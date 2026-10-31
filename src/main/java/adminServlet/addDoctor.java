package adminServlet;

import entity.Department;
import entity.Doctor;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpSession;

/**
 *
 * @author kuzeyerturk
 */
@WebServlet(name = "addDoctor", urlPatterns = {"/addDoctor"})
public class addDoctor extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int departmentId = Integer.parseInt(request.getParameter("department"));

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Department department = em.find(Department.class, departmentId);
            if (department != null) {
                Doctor doctor = new Doctor();
                doctor.setFullname(name);
                doctor.setUsername(username);
                doctor.setPassword(password);
                doctor.setDepartmentId(department);
                em.persist(doctor);
                em.getTransaction().commit();
                session.setAttribute("sucMsg", "Doctor added successfully.");
            } else {
                em.getTransaction().rollback();
                session.setAttribute("errorMsg", "Department not found.");
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            session.setAttribute("errorMsg", "An error occurred while adding the doctor.");
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }

        response.sendRedirect("http://localhost:8080/Appointment/admin/showDoctors.jsp");
    }
}