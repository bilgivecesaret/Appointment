
package patientServlet;

import entity.Department;
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

@WebServlet(name = "GetDepartments", urlPatterns = {"/getDepartments"})
public class GetDepartments extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Department> query = em.createQuery("SELECT d FROM Department d", Department.class);
            List<Department> departments = query.getResultList();

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().println("[");
            boolean first = true;
            for (Department department : departments) {
                if (!first) {
                    response.getWriter().println(",");
                }
                response.getWriter().println("{\"id\":" + department.getId() + ",\"name\":\"" + department.getName() + "\"}");
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
