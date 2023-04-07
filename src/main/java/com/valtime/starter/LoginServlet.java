package com.valtime.starter;

import com.valtime.starter.entities.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        HttpSession session = request.getSession();

        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");

        User user = new User();
        String message = "";
        boolean isRegistered = false;

        if (username != null && password != null){
            user.setUsername("username");
            user.setPassword("password");
            message = "Nice to meet you again, " + username + "!";
            isRegistered = true;
        } else {
            message = "Welcome! Please enter your credentials.";
        }

        session.setAttribute("user", user);
        session.setAttribute("message", message);
        session.setAttribute("isRegistered", isRegistered);

        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher("/login.jsp");
        dispatcher.forward(request, response);
    }
}
