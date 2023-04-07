package com.valtime.starter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ExperimentalServlet", value = "/experimental")
public class ExperimentalServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        response.setContentType("text/html");

        PrintWriter writer = response.getWriter();

        try {
            writer.println("Nice to meet you");
        } finally {
            writer.close();
        }

    }
}