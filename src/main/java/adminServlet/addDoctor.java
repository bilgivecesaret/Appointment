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

/**
 *
 * @author kuzeyerturk
 */
@WebServlet(name = "addDoctor", urlPatterns = {"/addDoctor"})
public class addDoctor extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String department = request.getParameter("department");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Doctor doctor = new Doctor();
        doctor.setFullname(name);
        Department d = new Department();
        d = em.find(Department.class, department);
        doctor.setDepartmentId(d);
        em.persist(doctor);
        em.getTransaction().commit();

        em.close();
        emf.close();

        response.sendRedirect("http://localhost:8080/Appointment/admin/showDoctors.jsp");
    }
}

