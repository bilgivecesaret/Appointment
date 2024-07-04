<!DOCTYPE html>
<html>
<head>
    <title>Doctor's Appointments</title>
</head>
<body>
    <h2>Your Appointments</h2>
    <c:if test="${not empty appointments}">
        <table border="1">
            <tr>
                <th>Patient ID</th>
                <th>Doctor ID</th>
                <th>Appointment Time</th>
            </tr>
            <c:forEach var="appointment" items="${appointments}">
                <tr>
                    <td>${appointment.patientID}</td>
                    <td>${appointment.doctorID}</td>
                    <td>${appointment.appointmentTime}</td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <c:if test="${empty appointments}">
        <p>No appointments found.</p>
    </c:if>
</body>
</html>