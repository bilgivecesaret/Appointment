
package patientServlet;

import entity.Appointment;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CancelAppointment", urlPatterns = {"/cancelAppointment"})
public class CancelAppointment extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String[] appointmentIds = request.getParameterValues("appointmentIds"); 
        HttpSession session = request.getSession();
        
        if (appointmentIds != null) {
            List<String> idsList = Arrays.asList(appointmentIds);
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
            EntityManager em = emf.createEntityManager();
            Appointment c = new Appointment();
            for(String ap:idsList){
                c = em.find(Appointment.class, Integer.parseInt(ap));
                em.getTransaction().begin();
                em.remove(c);
                em.getTransaction().commit();
            }
            em.close();
            emf.close();
            session.setAttribute("succMsg", "Your appointment has been cancelled.");            
        }else{
            session.setAttribute("errorMsg", "You haven't made any choices.");            
        }
        response.sendRedirect("http://localhost:8080/Appointment/patient/patientAppointment.jsp");
    }
}
