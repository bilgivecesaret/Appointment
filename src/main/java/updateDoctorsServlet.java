/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package adminServlet;

import entity.Doctor;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 *
 * @author kuzeyerturk
 */
public class updateDoctorsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int doctorId = Integer.parseInt(request.getParameter("doctorId"));
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String fullName = request.getParameter("fullName");
            String newDepartment = request.getParameter("department");

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("examplePU");
            EntityManager em = emf.createEntityManager();

            em.getTransaction().begin();
            Doctor doctor = em.find(Doctor.class, doctorId);
            if (doctor != null) {
                doctor.setDoctorUsername(username);
                doctor.setDoctorPassword(password);
                doctor.setDoctorFullName(fullName);
                doctor.setDoctorDepartment(newDepartment);
            }
            em.getTransaction().commit();

            em.close();
            emf.close();

            request.getRequestDispatcher("showDoctorsServlet").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        }
    }
}

