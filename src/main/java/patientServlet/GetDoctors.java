
package patientServlet;

import entity.Doctor;
import java.io.IOException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "GetDoctors", urlPatterns = {"/getDoctors"})
public class GetDoctors extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
        EntityManager em = emf.createEntityManager();

        String departmentIdParam = request.getParameter("departmentId");
        Integer departmentId = null;
        if (departmentIdParam != null && !departmentIdParam.isEmpty()) {
            departmentId = Integer.parseInt(departmentIdParam);
        }

        try {
            TypedQuery<Doctor> query;
            if (departmentId != null) {
                query = em.createQuery("SELECT d FROM Doctor d WHERE d.departmentId.id = :departmentId", Doctor.class);
                query.setParameter("departmentId", departmentId);
            } else {
                query = em.createQuery("SELECT d FROM Doctor d", Doctor.class);
            }
            List<Doctor> doctors = query.getResultList();

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().println("[");
            boolean first = true;
            for (Doctor doctor : doctors) {
                if (!first) {
                    response.getWriter().println(",");
                }
                response.getWriter().println("{\"id\":" + doctor.getId() + ",\"name\":\"" + doctor.getFullname() + "\"}");
                first = false;
            }
            response.getWriter().println("]");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
