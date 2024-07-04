
package patientServlet;

import dao.AppointmentDAO;
import dao.DoctorDAO;
import entity.Appointment;
import entity.Doctor;
import entity.Patient;
import java.io.IOException;
import java.util.ArrayList;
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

@WebServlet(name = "GetAppointment",urlPatterns = {"/getAppointment"})
public class GetAppointment extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Patient patient = (Patient) session.getAttribute("userObj");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
        EntityManager em = emf.createEntityManager();
        if (patient == null) {
            response.sendRedirect("http://localhost:8080/Appointment/patient/patientLogin.jsp");
            return;
        }        
        Appointment ap = new Appointment();
        List<Appointment> listAppointments = new ArrayList<>();
        Query q = em.createQuery("SELECT c FROM Appointment c");
        listAppointments = q.getResultList();
        
        /*
        DoctorDAO docDao = new DoctorDAO();
        List<Appointment> appList = appDao.findAllAppointmentsByPatient(patient.getId());
        List<List<Object>> appointments = new ArrayList<>();
        for(Appointment ap: appList){            
            List<Object> a = new ArrayList<>();
            a.add(ap.getAppointDate());
            a.add(ap.getAppointmentTime());
            a.add(docDao.findDepartment(ap.getDoctorId()));
            a.add(appDao.findDoctor(ap.getDoctorId()));
            appointments.add(a);
        }
        docDao.close();
        appDao.close();
        session.setAttribute("getAppointments", appointments);
*/
    }

}
