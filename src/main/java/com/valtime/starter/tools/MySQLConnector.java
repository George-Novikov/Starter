package com.valtime.starter.tools;

import com.valtime.starter.entities.User;
import jakarta.servlet.ServletContext;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class MySQLConnector {
    private final ServletContext context;
    public MySQLConnector(ServletContext context){
        this.context = context;
    }
    public ArrayList<User> selectAllUsers(){
        String expression = "SELECT * FROM users";
        String message = "";

        ArrayList<User> users = new ArrayList<User>();
        try {
            Class.forName("com.mysql.jdbc.Driver");

            try (Connection connection = getConnection(this.context)){
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery(expression);
                while (result.next()){
                    int id = result.getInt("ID");
                    String username = result.getString("username");
                    String password = result.getString("password");
                    String email = result.getString("email");
                    User user = new User(username, password, email);
                    user.setId(id);
                    users.add(user);
                }
            }

        } catch (Exception e){
            message = e.getMessage();
        }
        return users;
    }

    private static Connection getConnection(ServletContext context) throws SQLException, IOException {
        Properties properties = new Properties();
        try (InputStream inputStream = context.getResourceAsStream("WEB-INF/classes/database.properties")){
            properties.load(inputStream);
        }

        String url = properties.getProperty("url");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        return DriverManager.getConnection(url, username, password);
    }

}
