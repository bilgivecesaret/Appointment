<!DOCTYPE html>
<html>
<head>
    <title>Update Doctor</title>
</head>
<body>
    <h2>Update Doctor</h2>
    <form action="http://localhost:8080/Appointment/updateDoctor" method="post">
        <input type="hidden" name="doctorId" value="${doctor.id}">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" value="${doctor.username}" required><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" value="${doctor.password}" required><br>
        <label for="fullName">Full Name:</label>
        <input type="text" id="fullName" name="fullName" value="${doctor.fullname}" required><br>
        <label for="department">Department:</label>
        <input type="text" id="department" name="department" value="${doctor.department.id}" required><br>
        <input type="submit" value="Update Doctor">
    </form>
</body>
</html>