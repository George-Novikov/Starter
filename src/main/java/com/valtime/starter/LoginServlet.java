package com.valtime.starter;

import com.valtime.starter.entities.User;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        HttpSession session = request.getSession();
        
        User sessionUser = (User) session.getAttribute("user");
        String message = "Welcome! Please enter your credentials.";
        
        boolean isRegistered = false;

        if (sessionUser != null){
            message = "Nice to meet you again, " + sessionUser.getUsername() + "!";
            isRegistered = true;
        }
        
        session.setAttribute("message", message);
        session.setAttribute("isRegistered", isRegistered);

        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher("/login.jsp");
        dispatcher.forward(request, response);
    }
}
