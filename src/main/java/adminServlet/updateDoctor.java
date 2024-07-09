
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
import javax.servlet.http.HttpSession;


/**
 *
 * @author kuzeyerturk
 */
public class updateDoctor extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        try {
            int doctorId = Integer.parseInt(request.getParameter("doctorId"));
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String fullName = request.getParameter("fullName");
            int newDepartmentId = Integer.parseInt(request.getParameter("department"));

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
            EntityManager em = emf.createEntityManager();

            em.getTransaction().begin();
            Doctor doctor = em.find(Doctor.class, doctorId);
            Department department = em.find(Department.class, newDepartmentId);
            if (doctor != null && department != null) {
                doctor.setUsername(username);
                doctor.setPassword(password);
                doctor.setFullname(fullName);
                doctor.setDepartmentId(department);
                em.getTransaction().commit();
                session.setAttribute("sucMsg", "Doctor updated successfully.");
            } else {
                em.getTransaction().rollback();
                session.setAttribute("errorMsg", "Doctor or Department not found.");
            }
            em.close();
            emf.close();
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("errorMsg", "An error occurred while updating the doctor.");
        }
        response.sendRedirect("http://localhost:8080/Appointment/admin/showDoctors.jsp");
    }
}