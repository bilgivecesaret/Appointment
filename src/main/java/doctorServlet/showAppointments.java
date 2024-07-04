
package doctorServlet;

import entity.Doctor;
import entity.Appointment;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author kuzeyerturk
 */
@WebServlet(name = "showAppointments", urlPatterns = {"/showAppointments"})
public class showAppointments extends HttpServlet {

    private static final long serialVersionUID = 1L;

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Doctor doctor = (Doctor) session.getAttribute("doctor");

        if (doctor == null) {
            request.getRequestDispatcher("doctorLogin.jsp").forward(request, response);
            return;
        }

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
        EntityManager em = emf.createEntityManager();

        try {
            Query query = em.createQuery("SELECT a FROM Appointment a WHERE a.doctorId = :doctorId");
            query.setParameter("doctorId", doctor.getId());

            List<Appointment> appointments = query.getResultList();
            request.setAttribute("appointments", appointments);
            response.sendRedirect("http://localhost:8080/Appointment/doctor/showAppointments.jsp");
        } finally {
            em.close();
            emf.close();
        }
    }
}
