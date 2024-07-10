<%@ page import="entity.Doctor"%>
<%@ page import="entity.Department"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <title>Update Doctor</title>
    <style>
        .form-container {
            max-width: 600px;
            margin: auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
        }
        .form-group input, .form-group select {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>Update Doctor</h2>
        <form action="http://localhost:8080/Appointment/updateDoctor" method="post">
            <input type="hidden" name="doctorId" value="${doctor.id}">
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" value="${doctor.username}" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" value="${doctor.password}" required>
            </div>
            <div class="form-group">
                <label for="fullName">Full Name:</label>
                <input type="text" id="fullName" name="fullName" value="${doctor.fullname}" required>
            </div>
            <div class="form-group">
                <label for="department">Department:</label>
                <select id="department" name="department" required>
                    <c:forEach var="department" items="${departments}">
                        <option value="${department.id}" ${department.id == doctor.departmentId ? 'selected' : ''}>
                            ${department.name}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <input type="submit" value="Update Doctor">
        </form>
    </div>
</body>
</html>
