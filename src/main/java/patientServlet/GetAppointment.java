
package patientServlet;

import entity.Appointment;
import entity.Patient;
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

@WebServlet(name = "GetAppointment",urlPatterns = {"/getAppointment"})
public class GetAppointment extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Patient patient = (Patient) session.getAttribute("userObj");        
        if (patient == null) {
            response.sendRedirect("http://localhost:8080/Appointment/patient/patientLogin.jsp");
            return;
        }   
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("SELECT c FROM Appointment c WHERE c.patientId.id = :id",Appointment.class);
        q.setParameter("id", patient.getId());
        List<Appointment> appointments = q.getResultList();        
        session.setAttribute("getAppointments", appointments);
        em.close();
        emf.close();
        response.sendRedirect("http://localhost:8080/Appointment/patient/viewAppointment.jsp");
    }

}
