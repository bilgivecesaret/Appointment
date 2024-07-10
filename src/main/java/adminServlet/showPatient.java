/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package adminServlet;
import entity.Patient;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

/**
 *
 * @author kuzeyerturk
 */
@WebServlet(name = "showPatient", urlPatterns = {"/showPatient"})
public class showPatient extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
        EntityManager em = emf.createEntityManager();

        Query query = em.createQuery("SELECT p FROM Patient p",Patient.class);
        List<Patient> patients = query.getResultList();
        HttpSession session = request.getSession(false);
        session.setAttribute("pats", patients);
        response.sendRedirect("http://localhost:8080/Appointment/admin/showPatients.jsp");

        em.close();
        emf.close();
    }
}
