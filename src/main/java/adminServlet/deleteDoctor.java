/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package adminServlet;

import entity.Doctor;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpSession;

/**
 *
 * @author kuzeyerturk
 */
@WebServlet(name = "deleteDoctor", urlPatterns = {"/deleteDoctor"})
public class deleteDoctor extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int doctorId = Integer.parseInt(request.getParameter("doctorId"));

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Doctor doctor = em.find(Doctor.class, doctorId);
            if (doctor != null) {
                em.remove(doctor);
                em.getTransaction().commit();
                session.setAttribute("sucMsg", "Doctor deleted successfully.");
            } else {
                em.getTransaction().rollback();
                session.setAttribute("errorMsg", "Doctor not found.");
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            session.setAttribute("errorMsg", "An error occurred while deleting the doctor.");
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }

        response.sendRedirect("http://localhost:8080/Appointment/admin/showDoctors.jsp");
    }
}


