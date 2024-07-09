<%@ page import="entity.Department" %>
<%@ page import="javax.persistence.EntityManager" %>
<%@ page import="javax.persistence.EntityManagerFactory" %>
<%@ page import="javax.persistence.Persistence" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Doctor</title>
</head>
<body>
    <h2>Add Doctor</h2>
    <form action="http://localhost:8080/Appointment/addDoctor" method="post">
        <label for="name">Full Name:</label>
        <input type="text" id="name" name="name" required><br>

        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br>

        <label for="department">Select Department:</label>
        <select id="department" name="department" required>
            <%
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
                EntityManager em = emf.createEntityManager();
                List<Department> departments = em.createQuery("SELECT d FROM Department d", Department.class).getResultList();
                em.close();
                emf.close();

                for (Department department : departments) {
            %>
                <option value="<%= department.getId() %>"><%= department.getName() %></option>
            <%
                }
            %>
        </select>
        <br>
        <input type="submit" value="Add Doctor">
    </form>
</body>
</html>