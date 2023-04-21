package com.example.controller;

import com.example.service.impl.UserServiceImpl;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

@WebServlet("/sign-in")
public class LoginController extends HttpServlet {
    private UserServiceImpl userService;

    @Override



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        try {
            userService = new UserServiceImpl();

        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");


        if (userService.login(email, password) != null) {
            // successful login, redirect to home page
            HttpSession session = request.getSession();
            session.setAttribute("loggedInUser", email);
            response.sendRedirect(request.getContextPath() + "/home.jsp");
            
        } else {
            // invalid login, redirect back to login page with error message
            request.setAttribute("errorMessage", "Invalid email or password");
            request.getRequestDispatcher("/sign-in.jsp").forward(request, response);
        }
    }
}
