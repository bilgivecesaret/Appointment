
package patientServlet;

import entity.Appointment;
import entity.Department;
import entity.Doctor;
import entity.Patient;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
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
        
        
        HttpSession session = request.getSession();
        Patient patient = (Patient) session.getAttribute("userObj");
        String appoint_date = request.getParameter("appoint_date");
        String appoint_time = request.getParameter("appoint_time");
        int departmentId = Integer.parseInt(request.getParameter("departmentId"));
        int doctorId = Integer.parseInt(request.getParameter("doctorId"));
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
        EntityManager em = emf.createEntityManager();
        try { 
            Query queryPatientAppointments = em.createQuery("SELECT d FROM Appointment d WHERE d.patientId.id = :id", Appointment.class);
            queryPatientAppointments.setParameter("id", patient.getId());            
            Query queryDepartment = em.createQuery("SELECT d FROM Department d WHERE d.id = :id", Department.class);
            queryDepartment.setParameter("id", departmentId);
            Query queryDoctor = em.createQuery("SELECT d FROM Doctor d WHERE d.id = :id", Doctor.class);
            queryDoctor.setParameter("id", doctorId);

            
            List<Appointment> appointmentsOfPatient = queryPatientAppointments.getResultList();
            Department department = (Department) queryDepartment.getSingleResult();
            Doctor doctor = (Doctor) queryDoctor.getSingleResult(); 
            
            Query queryDoctorAppointments = em.createQuery("SELECT d FROM Appointment d WHERE d.doctorId.id = :id", Appointment.class);
            queryDoctorAppointments.setParameter("id",doctor.getId());
            List<Appointment> appointmentsOfDoctor = queryDoctorAppointments.getResultList();
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
            }else{            
                SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = dateFormatter.parse(appoint_date);
                SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");                
                Date time = timeFormatter.parse(appoint_time);
                for(Appointment ap: appointmentsOfPatient){
                    if((ap.getAppointDate().equals(date)) && (ap.getDoctorId().getDepartmentId().getId().equals(doctor.getDepartmentId().getId()) )){
                        session.setAttribute("errorMsg", "An appointment can only be made once from the same department on the same day.");
                        response.sendRedirect("http://localhost:8080/Appointment/patient/patientAppointment.jsp");
                        return;
                    }                    
                }
                for(Appointment ap: appointmentsOfDoctor){
                    if((ap.getAppointDate().equals(date)) && (ap.getAppointmentTime().equals(time))){
                        session.setAttribute("errorMsg", "This appointment time is full.");
                        response.sendRedirect("http://localhost:8080/Appointment/patient/patientAppointment.jsp");
                        return;
                    }
                }                
                Appointment appointment = new Appointment();
                appointment.setAppointDate(date);
                appointment.setAppointmentTime(time);
                appointment.setPatientId(patient);
                appointment.setDoctorId(doctor);
                em.getTransaction().begin();
                em.persist(appointment);
                em.getTransaction().commit();
                session.setAttribute("succMsg", "Your appointment has been created.");
                response.sendRedirect("http://localhost:8080/Appointment/patient/patientAppointment.jsp");
            }
        } catch (ParseException ex) {
            session.setAttribute("errorMsg", "Server Error.");
        } finally {
            em.close();
            emf.close();
        }      
    }
}
