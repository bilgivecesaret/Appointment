<!DOCTYPE html>
<html>
<head>
    <title>Update Doctor</title>
</head>
<body>
    <h2>Update Doctor</h2>
    <form action="updateDoctorServlet" method="post">
        <input type="hidden" name="doctorId" value="${doctor.doctorId}">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" value="${doctor.doctorUsername}" required><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" value="${doctor.doctorPassword}" required><br>
        <label for="fullName">Full Name:</label>
        <input type="text" id="fullName" name="fullName" value="${doctor.doctorFullName}" required><br>
        <label for="department">Department:</label>
        <input type="text" id="department" name="department" value="${doctor.doctorDepartment}" required><br>
        <input type="submit" value="Update Doctor">
    </form>
</body>
</html>