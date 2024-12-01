package com.ipd.reservation.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/accessDenied")
public class AccessDeniedServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward to JSP or handle the error
        request.getRequestDispatcher("/WEB-INF/jsp/accessDenied.jsp").forward(request, response);
    }
}

