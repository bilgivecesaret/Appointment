
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

@WebServlet(name = "patientRegister", urlPatterns = {"/patientRegister"})
public class PatientRegister extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String tc = request.getParameter("tc");
        String fullname = request.getParameter("fullname");
        String gender = request.getParameter("gender");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        HttpSession session = request.getSession();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery("SELECT d FROM Patient d WHERE d.tc = :tc", Patient.class);
            query.setParameter("tc", tc);
            boolean f1 = Validation.Validation.checkTc(tc);
            boolean f3 = Validation.Validation.checkPhone(phone);
            boolean f4 = Validation.Validation.checkEmail(email);
            boolean f5 = Validation.Validation.checkPassword(password);
            boolean f6 = password.equals(password2);            

            Patient patient;
            try {
                patient = (Patient) query.getSingleResult();
            } catch (NoResultException e) {
                patient = null;
            }

            if (patient != null) {
                session.setAttribute("errorMsg", "You have a registration. You cannot open a new registration.");
                response.sendRedirect("http://localhost:8080/Appointment/patient/signup.jsp");
            }else if(!f1){
                session.setAttribute("errorMsg", "TC number is incorrect.");
                response.sendRedirect("http://localhost:8080/Appointment/patient/signup.jsp");
            }else if(!f3){ 
                session.setAttribute("errorMsg", "Enter a mobile phone number starting with 5.");
                response.sendRedirect("http://localhost:8080/Appointment/patient/signup.jsp");
            }else if(!f4){ 
                session.setAttribute("errorMsg", "Email is incorrect.");
                response.sendRedirect("http://localhost:8080/Appointment/patient/signup.jsp");
            }else if(!f5){     
                session.setAttribute("errorMsg", "Minimum eight characters, "
                    + "at least one uppercase letter, one lowercase letter and one number");
                response.sendRedirect("http://localhost:8080/Appointment/patient/signup.jsp");
            }else if(!f6){
                session.setAttribute("errorMsg", "Passwords do not match.");
                response.sendRedirect("http://localhost:8080/Appointment/patient/signup.jsp");
            } else {
                patient = new Patient();
                patient.setTc(tc);
                patient.setFullname(fullname);
                patient.setGender(gender);
                patient.setPhone(phone);
                patient.setEmail(email);
                patient.setPassword(password);
                em.getTransaction().begin();
                em.persist(patient);
                em.getTransaction().commit();
                session.setAttribute("userObj", patient);
                response.sendRedirect("http://localhost:8080/Appointment/patient/patientLogin.jsp");
            }
        } finally {
            em.close();
            emf.close();
        }      
    }
}
