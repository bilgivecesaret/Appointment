
package adminServlet;

import entity.Department;
import entity.Doctor;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 *
 * @author kuzeyerturk
 */
public class updateDoctor extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int doctorId = Integer.parseInt(request.getParameter("doctorId"));
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String fullName = request.getParameter("fullName");
            String newDepartment = request.getParameter("department");

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("examplePU");
            EntityManager em = emf.createEntityManager();

            em.getTransaction().begin();
            Doctor doctor = em.find(Doctor.class, doctorId);
            if (doctor != null) {
                doctor.setUsername(username);
                doctor.setPassword(password);
                doctor.setFullname(fullName);
                Department d = new Department();
                d = em.find(Department.class, newDepartment);
                doctor.setDepartmentId(d);
            }
            em.getTransaction().commit();

            em.close();
            emf.close();

            request.getRequestDispatcher("showDoctors").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        }
    }
}



