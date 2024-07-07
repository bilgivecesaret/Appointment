
package patientServlet;

import entity.Appointment;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "UpdatePage", urlPatterns = {"/updatePage"})
public class UpdatePage extends HttpServlet {   
    
        
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        if(request.getParameter("appointmentIds")==null || request.getParameter("appointmentIds") == ""){
            session.setAttribute("errorMsg", "Make one choise.");
            response.sendRedirect("http://localhost:8080/Appointment/patient/viewAppointment.jsp");
            return;
        }
        int appointmentId = Integer.parseInt(request.getParameter("appointmentIds"));        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
        EntityManager em = emf.createEntityManager();        
        try {
            Appointment appointment = em.find(Appointment.class, appointmentId);            
            session.setAttribute("appointment", appointment);
            response.sendRedirect("http://localhost:8080/Appointment/patient/updateAppointment.jsp");
        } finally {
            em.close();
            emf.close();
        }
    }
}
