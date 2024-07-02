package patientServlet;

import entity.Patient;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "patientLogin", urlPatterns = {"/patientLogin"})
public class PatientLogin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
        EntityManager em = emf.createEntityManager();

        try {
            Query query = em.createQuery("SELECT d FROM Patient d WHERE d.email = :email AND d.password = :password", Patient.class);
            query.setParameter("email", email);
            query.setParameter("password", password);

            Patient patient;
            try {
                patient = (Patient) query.getSingleResult();
            } catch (NoResultException e) {
                patient = null;
            }

            if (patient != null) {
                session.setAttribute("userObj", patient);
                request.getRequestDispatcher("patient/index.jsp").forward(request, response);
            } else {
                session.setAttribute("errorMsg", "invalid email & password");
                request.getRequestDispatcher("patient/patientLogin.jsp").forward(request, response);
            }
        } finally {
            em.close();
            emf.close();
        }        
    }
}
