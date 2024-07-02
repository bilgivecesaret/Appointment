/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package adminServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

/**
 *
 * @author kuzeyerturk
 */
@WebServlet(name = "adminLoginServlet", urlPatterns = {"/adminLoginServlet"})
public class adminLogin extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("adminLogin.jsp");
        dispatcher.forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String adminUsername = "admin";
        String adminPassword = "123";

        if (username.equals(adminUsername) && password.equals(adminPassword)) {
            HttpSession session = request.getSession();
            session.setAttribute("admin", username);
            request.getRequestDispatcher("adminHome.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("notFound.jsp").forward(request, response);
        }
    }
}

