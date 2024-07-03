<!DOCTYPE html>
<html>
<head>
    <title>Admin Home</title>
</head>
<body>
    <h2>Welcome, Admin</h2>
    <ul>
        <li><a href="showDoctors">Show Doctors</a></li>
        <li><a href="showPatient">Show Patients</a></li>
        <li><a href="addDoctor">Add Doctor</a></li>
        <li><a href="updateDoctor">Update Doctor</a></li>
        <li><a href="deleteDoctor">Delete Doctor</a></li>
    </ul>
    <form action="logoutServlet" method="post">
        <input type="submit" value="Logout">
    </form>
</body>
</html>