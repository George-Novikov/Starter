package com.valtime.starter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.valtime.starter.entities.User;
import com.valtime.starter.tools.MySQLConnector;
import com.valtime.starter.tools.Validator;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
        
        User currentUser = new User(username, password, email);
        session.setAttribute("user", currentUser);
        boolean isRegistered = false;
        boolean isEmpty = Validator.checkInput(currentUser);
        String message = "";
        ArrayList<User> users = null;
        
        PrintWriter writer = response.getWriter();
        MySQLConnector connector = null;

        try {
            connector = new MySQLConnector(context);
            writer.println("<h3>The connection to DB is successful</h3><br>");
            
            if (!isEmpty) {
            	User dbUser = connector.selectUser(currentUser);
            	if (dbUser != null) {
                	isRegistered = true;
                } else {
                	connector.insertUser(currentUser);
                }
            } else {
            	RequestDispatcher dispatcher = context.getRequestDispatcher("/login.jsp");
            	message = "Please fill empty fields!";
            	session.setAttribute("message", message);
            	dispatcher.forward(request, response);
            }
            
            users = connector.selectAllUsers();
        } catch (Exception e){
            writer.println(e.getMessage());
        }
        
        if (isRegistered) {
    		message = "Welcome back!";
    	} else {
    		message = "Registration successful.";
    	}
    	writer.println("<br>" + message + " Your credentials: " + username + " : " + password + "<br><br>");

        for (User user : users){
            writer.println("<li>" + user.getId() + ": "+ user.getUsername() + " - " + user.getPassword() + " - " + user.getEmail() + "</li>");
        }
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
