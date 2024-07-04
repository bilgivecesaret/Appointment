<!DOCTYPE html>
<html>
<head>
    <title>Admin Home</title>
</head>
<body>
    <h2>Welcome, Admin</h2>
    <ul>
        <li><a href="http://localhost:8080/Appointment/admin/showDoctors">Show Doctors</a></li>
        <li><a href="http://localhost:8080/Appointment/admin/showPatients">Show Patients</a></li>
        <li><a href="http://localhost:8080/Appointment/admin/addDoctor">Add Doctor</a></li>
        <li><a href="http://localhost:8080/Appointment/admin/updateDoctor">Update Doctor</a></li>
        <li><a href="http://localhost:8080/Appointment/admin/deleteDoctor">Delete Doctor</a></li>
    </ul>
    <form action="http://localhost:8080/Appointment/adminLogout" method="post">
        <input type="submit" value="Logout">
    </form>
</body>
</html>