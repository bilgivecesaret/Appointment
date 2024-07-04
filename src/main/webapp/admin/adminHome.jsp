<!DOCTYPE html>
<html>
<head>
    <title>Admin Home</title>
</head>
<body>
    <h2>Welcome, Admin</h2>
    <ul>
        <li><a href="http://localhost:8080/Appointment/showDoctors">Show Doctors</a></li>
        <li><a href="http://localhost:8080/Appointment/showPatient">Show Patients</a></li>
        <li><a href="http://localhost:8080/Appointment/addDoctor">Add Doctor</a></li>
        <li><a href="http://localhost:8080/Appointment/updateDoctor">Update Doctor</a></li>
        <li><a href="http://localhost:8080/Appointment/deleteDoctor">Delete Doctor</a></li>
    </ul>
    <form action="logoutServlet" method="post">
        <input type="submit" value="Logout">
    </form>
</body>
</html>