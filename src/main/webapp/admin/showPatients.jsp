<!DOCTYPE html>
<html>
<head>
    <title>Show Patients</title>
</head>
<body>
    <h2>Patients</h2>
    <table border="1">
        <tr>
            <th>Patient ID</th>
            <th>Full Name</th>
            <th>TCKNO</th>
            <th>Birthday</th>
        </tr>
        <c:forEach var="patient" items="${patients}">
            <tr>
                <td>${patient.patientId}</td>
                <td>${patient.patientFullName}</td>
                <td>${patient.patientTCKNO}</td>
                <td>${patient.patientBirthday}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>