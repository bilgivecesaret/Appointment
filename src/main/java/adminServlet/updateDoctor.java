
package adminServlet;

import entity.Department;
import entity.Doctor;
import java.io.IOException;
import java.util.List;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int doctorId = Integer.parseInt(request.getParameter("id"));

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
        EntityManager em = emf.createEntityManager();

        Doctor doctor = em.find(Doctor.class, doctorId);
        List<Department> departments = em.createQuery("SELECT d FROM Department d", Department.class).getResultList();

        em.close();
        emf.close();

        HttpSession session = request.getSession();
        session.setAttribute("doctor", doctor);
        session.setAttribute("departments", departments);

        response.sendRedirect("admin/updateDoctor.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int doctorId = Integer.parseInt(request.getParameter("id"));
        String fullname = request.getParameter("fullname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int departmentId = Integer.parseInt(request.getParameter("department"));

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Doctor doctor = em.find(Doctor.class, doctorId);
        doctor.setFullname(fullname);
        doctor.setUsername(username);
        doctor.setPassword(password);
        doctor.setDepartmentId(em.find(Department.class, departmentId));
        em.getTransaction().commit();

        em.close();
        emf.close();

        HttpSession session = request.getSession();
        session.setAttribute("sucMsg", "Doctor updated successfully");

        response.sendRedirect("admin/showDoctors.jsp");
    }
}