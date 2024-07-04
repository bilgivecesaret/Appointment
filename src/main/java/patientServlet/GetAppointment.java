
package patientServlet;

import entity.Appointment;
import entity.Patient;
import java.io.IOException;
import java.util.ArrayList;
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

@WebServlet(urlPatterns = {"/getAppointment"})
public class GetAppointment extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
        EntityManager em = emf.createEntityManager();                                     
        List<Appointment> list = new ArrayList<>();
        HttpSession session = request.getSession();
        Patient pt = (Patient) session.getAttribute("userObj");
        Query query = em.createQuery("SELECT c FROM Appointment c WHERE c.id = :id1", Appointment.class);
        query.setParameter("id1", pt.getId());
        list = query.getResultList();
        session.setAttribute("list", list);
        response.sendRedirect("http://localhost:8080/Appointment/patient/viewAppointment.jsp");
    }

}
