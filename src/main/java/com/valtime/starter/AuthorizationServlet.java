package com.valtime.starter;

import com.valtime.starter.entities.User;
import com.valtime.starter.tools.MySQLConnector;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.*;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.nio.file.Files;

@WebServlet(name = "AuthorizationServlet", value = "/AuthorizationServlet")
public class AuthorizationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        ServletContext context = getServletContext();
        HttpSession session = request.getSession();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        
        session.setAttribute("user", new User(username, password, email));

        PrintWriter writer = response.getWriter();
        MySQLConnector connector = null;
        
        User currentUser = new User(username, password, email);
        boolean isRegistered = false;
        String message = "";
        ArrayList<User> users = null;

        try {
            connector = new MySQLConnector(context);
            writer.println("<h3>The connection to DB is successful</h3><br>");
            User dbUser = connector.selectUser(currentUser);
            if (dbUser != null) {
            	isRegistered = true;
            } else {
            	connector.insertUser(currentUser);
            }
            
            users = connector.selectAllUsers();
        } catch (Exception e){
            writer.println(e.getMessage());
        }

        try {
        	if (isRegistered) {
        		message = "Welcome back!";
        	} else {
        		message = "Registration successful.";
        	}
        	
            writer.println("<br>" + message + " Your credentials: " + username + " : " + password + "<br><br>");

            for (User user : users){
                writer.println("<li>" + user.getId() + ": "+ user.getUsername() + " - " + user.getPassword() + " - " + user.getEmail() + "</li>");
            }
        } finally{
            writer.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
