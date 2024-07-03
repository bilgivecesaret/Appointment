
package patientServlet;

import entity.Patient;
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

@WebServlet(name = "ChangePassword", urlPatterns = {"/changePassword"})
public class ChangePassword extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");
        HttpSession session = request.getSession(false);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
        EntityManager em = emf.createEntityManager();
        boolean flag = Validation.Validation.checkPassword(newPassword);
        if (!(newPassword.equals(confirmPassword))) {
            session.setAttribute("errorMsg", "Passwords do not match");
            response.sendRedirect("http://localhost:8080/Appointment/patient/change_password.jsp");
        }else if(flag){    
            Patient patient = (Patient) session.getAttribute("userObj");
            em.getTransaction().begin();
            patient.setPassword(newPassword);
            em.merge(patient);
            em.getTransaction().commit();
            em.close();
            emf.close();            
            response.sendRedirect("http://localhost:8080/Appointment/patient/index.jsp");
        } else {
            session.setAttribute("errorMsg", "Minimum eight characters, at least one uppercase letter, one lowercase letter and one number");
            response.sendRedirect("http://localhost:8080/Appointment/patient/change_password.jsp");
        }       
        
    }
}
