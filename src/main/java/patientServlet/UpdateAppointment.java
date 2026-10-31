
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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "UpdateAppointment", urlPatterns = {"/updateAppointment"})
public class UpdateAppointment extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int appointmentId = Integer.parseInt(request.getParameter("id"));
        String appoint_date = request.getParameter("appoint_date");
        String appoint_time = request.getParameter("appoint_time");
        int doctorId = Integer.parseInt(request.getParameter("doctorId"));
        HttpSession session = request.getSession();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
        EntityManager em = emf.createEntityManager();
        try {
            Query queryAppointment = em.createQuery("SELECT d FROM Appointment d WHERE d.id = :id", Appointment.class);
            queryAppointment.setParameter("id", appointmentId);
            Query queryDoctor = em.createQuery("SELECT d FROM Doctor d WHERE d.id = :id", Doctor.class);
            queryDoctor.setParameter("id", doctorId);
            
            Patient patient = (Patient) session.getAttribute("userObj");
            Appointment appointment = new Appointment();
            Doctor doctor = new Doctor();
            try {
                appointment = (Appointment) queryAppointment.getSingleResult();                
                doctor = (Doctor) queryDoctor.getSingleResult();
            } catch (NoResultException e) {
                session.setAttribute("errorMsg", "Server Connection Error.");
            }
            if (doctor == null) {
                session.setAttribute("errorMsg", "Select doctor.");
                response.sendRedirect("http://localhost:8080/Appointment/patient/updateAppointment.jsp");
            }else if(appoint_date == null || appoint_date == ""){ 
                session.setAttribute("errorMsg", "Select appointment date.");
                response.sendRedirect("http://localhost:8080/Appointment/patient/updateAppointment.jsp");
            }else if(appoint_time == null || appoint_time == ""){  
                session.setAttribute("errorMsg", "Select appointment time.");
                response.sendRedirect("http://localhost:8080/Appointment/patient/updateAppointment.jsp");
            } else {
                SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = dateFormatter.parse(appoint_date);
                SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");                
                Date time = timeFormatter.parse(appoint_time);
                appointment.setAppointDate(date);
                appointment.setAppointmentTime(time);
                appointment.setPatientId(patient);
                appointment.setDoctorId(doctor);
                em.getTransaction().begin();
                em.merge(appointment);
                em.getTransaction().commit();
                session.setAttribute("succMsg", "Your appointment has been updated.");
                response.sendRedirect("http://localhost:8080/Appointment/patient/updateAppointment.jsp");
            }
        } catch (ParseException ex) {
            session.setAttribute("errorMsg", "User Object Error.");
        } finally {
            em.close();
            emf.close();
        }      
    }
}
