<!DOCTYPE html>
<html>
<head>
    <title>Doctor Login</title>
</head>
<body>
    <h2>Doctor Login</h2>
    <form action="http://localhost:8080/Appointment/doctorLogin" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br>
        <input type="submit" value="Login">
    </form>
</body>
</html>
