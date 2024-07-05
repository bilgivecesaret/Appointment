<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-9" pageEncoding="ISO-8859-9"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-9">
    <title>Insert title here</title>
    <%@include file="../component/allcss.jsp"%>
    <style type="text/css">
        .paint-card {
            box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
        }
    </style>
</head>
<body>
    <%@include file="../component/navbar.jsp"%>
<div class="container p-5">
    <div class="row">
        <div class="col-md-4 offset-md-4">
            <div class="card paint-card">
                <div class="card-body">
                    <p class="fs-4 text-center">Patient Register</p>

                    <c:if test="${not empty sucMsg }">
                        <p class="text-center text-success fs-3">${sucMsg}</p>
                        <c:remove var="sucMsg" scope="session" />
                    </c:if>

                    <c:if test="${not empty errorMsg }">
                        <p class="text-center text-danger fs-3">${errorMsg}</p>
                        <c:remove var="errorMsg" scope="session" />
                    </c:if>


                    <form action="http://localhost:8080/Appointment/patientRegister" method="post">
                        <div class="mb-3">
                            <label for="tc" class="form-label">TC Number</label> 
                            <input required name="tc" type="text" class="form-control">
                        </div>
                        <div class="mb-3">
                            <label for="fullname" class="form-label">Full Name</label> 
                            <input required name="fullname" type="text" class="form-control">
                        </div>
                        <div class="mb-3">
                            <label for="gender">Gender</label> 
                            <select class="form-control" name="gender" required>
                                <option value="">--select--</option>
                                <option value="MALE">MALE</option>
                                <option value="FEMALE">FEMALE</option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="phone" class="form-label">Phone Number</label> 
                            <input required name="phone" type="text" class="form-control">
                        </div>
                        <div class="mb-3">
                            <label for="email" class="form-label">Email Address</label> 
                            <input required name="email" type="email" class="form-control">
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">Password</label> 
                            <input required name="password" type="password" class="form-control">
                        </div>
                        <div class="mb-3">
                            <label for="password2" class="form-label">Confirm Password</label> 
                            <input required name="password2" type="password" class="form-control">
                        </div>
                        <button type="submit" class="btn bg-success text-white col-md-12">Save</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>




</body>
</html>