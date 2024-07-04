<%@ page import="entity.Doctor" %>
<%@ page import="javax.persistence.EntityManager" %>
<%@ page import="javax.persistence.EntityManagerFactory" %>
<%@ page import="javax.persistence.Persistence" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Delete Doctor</title>
</head>
<body>
    <h2>Delete Doctor</h2>
    <form action="http://localhost:8080/Appointment/deleteDoctor" method="post">
        <label for="doctorId">Select Doctor:</label>
        <select id="doctorId" name="doctorId" required>
            <%
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
                EntityManager em = emf.createEntityManager();
                List<Doctor> doctors = em.createQuery("SELECT d FROM Doctor d", Doctor.class).getResultList();
                em.close();
                emf.close();

                for (Doctor doctor : doctors) {
            %>
                <option value="<%= doctor.getDoctorId() %>"><%= doctor.getDoctorFullName() %> - <%= doctor.getDoctorDepartment() %></option>
            <%
                }
            %>
        </select>
        <br>
        <input type="submit" value="Delete Doctor">
    </form>
</body>
</html>