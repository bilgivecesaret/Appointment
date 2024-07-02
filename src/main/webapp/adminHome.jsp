<!DOCTYPE html>
<html>
<head>
    <title>Admin Home</title>
</head>
<body>
    <h2>Welcome, Admin</h2>
    <ul>
        <li><a href="showDoctorsServlet">Show Doctors</a></li>
        <li><a href="showPatientsServlet">Show Patients</a></li>
        <li><a href="addDoctorServlet">Add Doctor</a></li>
    </ul>
    <form action="logoutServlet" method="post">
        <input type="submit" value="Logout">
    </form>
</body>
</html>