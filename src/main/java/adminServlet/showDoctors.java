
package adminServlet;

import entity.Doctor;
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
import javax.servlet.http.HttpSession;


/**
 *
 * @author kuzeyerturk
 */
@WebServlet(name = "showDoctors", urlPatterns = {"/showDoctors"})
public class showDoctors extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
        EntityManager em = emf.createEntityManager();
        
        Query query = em.createQuery("SELECT d FROM Doctor d",Doctor.class);
        List<Doctor> doctors = query.getResultList();
        HttpSession session = request.getSession(false);
        
        session.setAttribute("docs", doctors);
        response.sendRedirect("http://localhost:8080/Appointment/admin/showDoctors.jsp");
        
        em.close();
        emf.close();
    }
}
