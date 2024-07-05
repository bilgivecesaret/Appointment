
package patientServlet;

import entity.Appointment;
import entity.Department;
import entity.Doctor;
import entity.Patient;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AddAppointment", urlPatterns = {"/addAppointment"})
public class AddAppointment extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String appoint_date = request.getParameter("appoint_date");
        String appoint_time = request.getParameter("appoint_time");
        int departmentId = Integer.parseInt(request.getParameter("departmentId"));
        int doctorId = Integer.parseInt(request.getParameter("doctorId"));
        HttpSession session = request.getSession();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
        EntityManager em = emf.createEntityManager();
        try { 
            Query queryDepartment = em.createQuery("SELECT d FROM Department d WHERE d.id = :id", Department.class);
            queryDepartment.setParameter("id", departmentId);
            Query queryDoctor = em.createQuery("SELECT d FROM Doctor d WHERE d.id = :id", Doctor.class);
            queryDoctor.setParameter("id", doctorId);

            Patient patient = (Patient) session.getAttribute("userObj");            
            Department department;
            Doctor doctor;
            try {
                department = (Department) queryDepartment.getSingleResult();
                doctor = (Doctor) queryDoctor.getSingleResult();
            } catch (NoResultException e) {
                department = null;
                doctor = null;
            }

            if (department == null) {
                session.setAttribute("errorMsg", "Select department.");
                response.sendRedirect("http://localhost:8080/Appointment/patient/patientAppointment.jsp");
            }else if (doctor == null) {
                session.setAttribute("errorMsg", "Select doctor.");
                response.sendRedirect("http://localhost:8080/Appointment/patient/patientAppointment.jsp");
            }else if(appoint_date == null || appoint_date == ""){ 
                session.setAttribute("errorMsg", "Select appointment date.");
                response.sendRedirect("http://localhost:8080/Appointment/patient/patientAppointment.jsp");
            }else if(appoint_time == null || appoint_time == ""){  
                session.setAttribute("errorMsg", "Select appointment time.");
                response.sendRedirect("http://localhost:8080/Appointment/patient/patientAppointment.jsp");
            } else {
                SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = dateFormatter.parse(appoint_date);
                SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");                
                Date time = timeFormatter.parse(appoint_time);
                Appointment appointment = new Appointment();
                appointment.setAppointDate(date);
                appointment.setAppointmentTime(time);
                appointment.setPatientId(patient);
                appointment.setDoctorId(doctor);
                em.getTransaction().begin();
                em.persist(appointment);
                em.getTransaction().commit();
                RequestDispatcher requestDispatcher;
                requestDispatcher=request.getRequestDispatcher("/patient/viewAppointment.jsp");
                requestDispatcher.forward(request, response);
            }
        } catch (ParseException ex) {
            session.setAttribute("errorMsg", "Server Error.");
        } finally {
            em.close();
            emf.close();
        }      
    }
}
