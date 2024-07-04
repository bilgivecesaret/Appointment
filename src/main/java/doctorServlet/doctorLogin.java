/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package doctorServlet;

import entity.Doctor;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.persistence.Query;

/**
 *
 * @author kuzeyerturk
 */
@WebServlet(name = "doctorLogin", urlPatterns = {"/doctorLogin"})
public class doctorLogin extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
        EntityManager em = emf.createEntityManager();

        try {
            Query query = em.createQuery("SELECT d FROM Doctor d WHERE d.doctorUsername = :username AND d.doctorPassword = :password", Doctor.class);
            query.setParameter("username", username);
            query.setParameter("password", password);

            Doctor doctor;
            try {
                doctor = (Doctor) query.getSingleResult();
            } catch (NoResultException e) {
                doctor = null;
            }

            if (doctor != null) {
                HttpSession session = request.getSession();
                session.setAttribute("doctor", doctor);
                response.sendRedirect("http://localhost:8080/Appointment/doctor/doctorHome.jsp");
            } else {
                response.sendRedirect("http://localhost:8080/Appointment/notFound.jsp");
            }
        } finally {
            em.close();
            emf.close();
        }
    }
}