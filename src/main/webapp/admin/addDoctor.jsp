<!DOCTYPE html>
<html>
<head>
    <title>Add Doctor</title>
</head>
<body>
    <h2>Add Doctor</h2>
    <form action="http://localhost:8080/Appointment/addDoctor" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br>
        <label for="fullName">Full Name:</label>
        <input type="text" id="fullName" name="fullName" required><br>
        <label for="department">Department:</label>
        <input type="text" id="department" name="department" required><br>
        <input type="submit" value="Add Doctor">
    </form>
</body>
</html>