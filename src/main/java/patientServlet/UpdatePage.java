
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
        
        String[] apIds = request.getParameterValues("appointmentIds");
        HttpSession session = request.getSession();
        if(apIds ==null || apIds.length <= 0){
            session.setAttribute("errorMsg", "You haven't make any choice.");
            response.sendRedirect("http://localhost:8080/Appointment/patient/viewAppointment.jsp");
        }else if (apIds.length==1) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
            EntityManager em = emf.createEntityManager();
            Appointment c = new Appointment();
            c = em.find(Appointment.class, Integer.parseInt(apIds[0]));
            session.setAttribute("appointment", c);            
            em.close();
            emf.close();
            response.sendRedirect("http://localhost:8080/Appointment/patient/updateAppointment.jsp");
        }else{
            session.setAttribute("errorMsg", "You can make only one choice.");
            response.sendRedirect("http://localhost:8080/Appointment/patient/viewAppointment.jsp");
        }        
    }
}
