
package patientServlet;

import java.io.IOException;
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
        try {
			String fullName = request.getParameter("fullname");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			HttpSession session = request.getSession();

			boolean f = true;

			if (f) {              
                            session.setAttribute("sucMsg", "Register Sucessfully");
                            response.sendRedirect("signup.jsp");

			} else {
                            session.setAttribute("errorMsg", "Something wrong on server");
                            response.sendRedirect("signup.jsp");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }

}
