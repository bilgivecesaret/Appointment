package patientServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Patient;

@WebServlet(name = "patientLogin", urlPatterns = {"/patientLogin"})
public class PatientLogin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();
        String patient = "abc";

        if (patient != null) {
                session.setAttribute("userObj", patient);
                response.sendRedirect("patient/index.jsp");
        } else {
                session.setAttribute("errorMsg", "invalid email & password");
                response.sendRedirect("patient/patientLogin.jsp");
        }
        
    }
}
