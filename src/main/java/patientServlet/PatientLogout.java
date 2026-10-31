package patientServlet;

import entity.Patient;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "PatientLogout", urlPatterns = {"/patientLogout"})
public class PatientLogout extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        Patient patient = (Patient) session.getAttribute("userObj");
        if(patient == null){
            response.sendRedirect("http://localhost:8080/Appointment/patient/patientLogin.jsp");
            return;
        }
        session.removeAttribute("userObj");
        session.removeAttribute("patient_appointments");
        session.setAttribute("succMsg", "Patient Logout Sucessfully");
        response.sendRedirect("http://localhost:8080/Appointment/patient/patientLogin.jsp");        
    }
}
