<%@ page import="entity.Doctor"%>
<%@ page import="entity.Department"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Doctor List</title>
    <%@include file="../component/allcss.jsp"%>
    <style type="text/css">
        .paint-card {
            box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
        }
        .backImg {
            background: linear-gradient(rgba(0, 0, 0, .4), rgba(0, 0, 0, .4)),
            url("img/hosp.jpg");
            height: 30vh;
            width: 100%;
            background-size: cover;
            background-repeat: no-repeat;
        }
    </style>
</head>
<body>
    <%@include file="./navbar.jsp"%>
    <div class="container-fluid backImg p-5">
        <p class="text-center fs-2 text-white">Doctor List</p>
    </div>
    <div class="container p-3">
        <div class="row">
            <div class="col-md-9">
                <div class="card paint-card">
                    <div class="card-body">
                        <p class="fs-4 fw-bold text-center text-success">Doctor List</p>
                        <table class="table">
                            <thead>
                                <tr>
                                    <th scope="col">Doctor ID</th>
                                    <th scope="col">Username</th>
                                    <th scope="col">Full Name</th>
                                    <th scope="col">Department</th>
                                    <th scope="col">Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="doctor" items="${doctors}">
                                    <tr>
                                        <td>${doctor.id}</td>
                                        <td>${doctor.username}</td>
                                        <td>${doctor.fullName}</td>
                                        <td>${doctor.departmentId}</td>
                                        <td>
                                            <a href="updateDoctor.jsp?id=${doctor.id}" class="btn btn-primary">Edit</a>
                                            <a href="deleteDoctor?id=${doctor.id}" class="btn btn-danger">Delete</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            <div class="col-md-3 p-3">
                <img alt="" src="../img/doct9.jpg" height="200px" width="200px">
            </div>
        </div>
    </div>
</body>
</html>