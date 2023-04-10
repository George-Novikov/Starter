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

    public User selectUser(User currentUser){
        String expression = "SELECT * FROM users WHERE username=? AND password=?";
        String message = "";
        User user = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            try (Connection connection = getConnection(this.context)){
                PreparedStatement statement = connection.prepareStatement(expression);
                statement.setString(1, currentUser.getUsername());
                statement.setString(2, currentUser.getPassword());
                ResultSet result = statement.executeQuery();
                while (result.next()){
                    int id = result.getInt("ID");
                    String uname = result.getString("username");
                    String pwd = result.getString("password");
                    String email = result.getString("email");
                    user = new User(uname, pwd, email);
                    user.setId(id);
                }
            }
        } catch (Exception e){
            message = e.getMessage();
        }
        return user;
    }
    
    public int insertUser(User user) {
    	String expression = "INSERT users (username, password, email) VALUES ('" + user.getUsername() + "', '" + user.getPassword() + "', '" + user.getEmail() + "' )";
    	String message = "";
    	int rows = 0;
    	try {
    		Class.forName("com.mysql.jdbc.Driver");
    		try (Connection connection = getConnection(this.context)){
    			Statement statement = connection.createStatement();
    			rows = statement.executeUpdate(expression);
    		}
    	} catch (Exception e) {
    		message = e.getMessage();
    	}
    	return rows;
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
