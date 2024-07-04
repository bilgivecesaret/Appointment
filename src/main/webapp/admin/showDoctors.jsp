<!DOCTYPE html>
<html>
<head>
    <title>Show Doctors</title>
</head>
<body>
    <h2>Doctors</h2>
    <table border="1">
        <tr>
            <th>Doctor ID</th>
            <th>Username</th>
            <th>Full Name</th>
            <th>Department</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="doctor" items="${doctors}">
            <tr>
                <td>${doctor.doctorId}</td>
                <td>${doctor.doctorFullName}</td>
                <td>${doctor.doctorDepartment}</td>
                <td>
                    <form action="http://localhost:8080/Appointment/admin/deleteDoctor" method="post" style="display:inline;">
                        <input type="hidden" name="doctorId" value="${doctor.doctorId}">
                        <input type="submit" value="Delete">
                    </form>
                    <form action="http://localhost:8080/Appointment/admin/updateDoctor" method="get" style="display:inline;">
                        <input type="hidden" name="doctorId" value="${doctor.doctorId}">
                        <input type="submit" value="Update">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>