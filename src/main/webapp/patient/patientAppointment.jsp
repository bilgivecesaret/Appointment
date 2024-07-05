<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<%@page import="entity.Doctor"%>
<%@page import="entity.Department"%>
<%@page import="dao.DoctorDAO"%>
<%@page import="dao.DepartmentDAO"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-9" pageEncoding="ISO-8859-9"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-9">
        <title>Patient Appointment</title>
        <%@include file="../component/allcss.jsp"%>
        <style type="text/css">
            .paint-card {
                    box-shadow: 0 0 8px 0 rgba(0, 0, 0, 0.3);
            }

            .backImg {
                    background: linear-gradient(rgba(0, 0, 0, .4), rgba(0, 0, 0, .4)),
                            url("img/background5.jpg");
                    height: 40vh;
                    width: 100%;
                    background-size: cover;
                    background-repeat: no-repeat;
            }
        </style>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>
    <body>
        <c:if test="${ empty userObj }">
            <c:redirect url="http://localhost:8080/Appointment/patient/patientLogin.jsp"></c:redirect>
        </c:if>
        <%@include file="./navbar.jsp"%>
        <div class="container-fulid backImg p-5">
            <p class="text-center fs-2 text-white"></p>
        </div>
        <div class="container p-3">
            <div class="row">
                <div class="col-md-6 p-5">
                    <img alt="" src="../img/doctor1.jpg" height="500px" width="450x">
                </div>
                <div class="col-md-6">
                    <div class="card paint-card">
                        <div class="card-body">
                            <p class="text-center fs-3">Patient Appointment</p>
                                <c:if test="${not empty errorMsg}">
                                        <p class="fs-4 text-center text-danger">${errorMsg}</p>
                                        <c:remove var="errorMsg" scope="session" />
                                </c:if>
                                <c:if test="${not empty succMsg}">
                                        <p class=" fs-4 text-center text-success">${succMsg}</p>
                                        <c:remove var="succMsg" scope="session" />
                                </c:if>
                                <form class="row g-3" action="http://localhost:8080/Appointment/appAppointment" method="post">
                                    <input type="hidden" name="userid" value="${userObj.id }"> 
                                    <div class="col-md-6">
                                        <label for="department" class="form-label">Department</label> 
                                        <select required class="form-control" name="department" id="one" onchange="showSecondDropdown()">
                                            <option value="">--select--</option>                                              
                                        </select>
                                    </div>
                                    <div class="col-md-6">
                                        <label for="appoint_date" class="form-label">Appointment Date</label> 
                                        <input type="date" class="form-control" required name="appoint_date">
                                    </div>
                                    <div class="col-md-6">
                                        <label for="appoint_time" class="form-label">Appointment Time</label>
                                        <select class="form-control" name="appoint_time" required>
                                            <option value="">--Select Time--</option>
                                            <option value="09:00:00">09:00</option>
                                            <option value="09:30:00">09:30</option>
                                            <option value="10:00:00">10:00</option>
                                            <option value="10:30:00">10:30</option>
                                            <option value="11:00:00">11:00</option>
                                            <option value="11:30:00">11:30</option>
                                            <option value="13:30:00">13:30</option>
                                            <option value="14:00:00">14:00</option>
                                            <option value="14:30:00">14:30</option>
                                            <option value="15:00:00">15:00</option>
                                            <option value="15:30:00">15:30</option>
                                            <option value="16:00:00">16:00</option>
                                            <option value="16:30:00">16:30</option>                                            
                                        </select>
                                    </div>    
                                    <div class="col-md-6">
                                        <label for="doctor" class="form-label">Doctor</label> 
                                        <select required class="form-control" name="doctor" id="two" disabled>
                                            <option value="">--select--</option>
                                        </select>
                                    </div>
                                    <button class="col-md-6 offset-md-3 btn btn-success">Save</button>                                    
                                </form>
                                <div class="row g-3" style="margin-top: 10px">
                                    <a href="http://localhost:8080/Appointment/patient/patientAppointment.jsp" >
                                            <button class="col-md-6 offset-md-3 btn btn-success">Clear</button>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
            </div>
        </div>
        <%@include file="../component/footer.jsp"%>
        <script>
        function showSecondDropdown() {
            var firstSelect = document.getElementById("one");
            var secondSelect = document.getElementById("two");
            
            if (firstSelect.value === "") {
                secondSelect.setAttribute("disabled", "disabled");
            } else {
                secondSelect.removeAttribute("disabled");
            }
        }
        $(document).ready(function() {
            // Departmanları yükle
            $.ajax({
                url: 'http://localhost:8080/Appointment/getDepartments',
                method: 'GET',
                success: function(data) {
                    data.forEach(function(department) {
                        $('#one').append(new Option(department.name, department.id));
                    });
                }
            });

            // Departman seçildiğinde doktorları yükle
            $('#one').on('change', function() {
                var departmentId = $(this).val();
                $('#two').empty().append(new Option("---Select---", ""));
                if (departmentId) {
                    $.ajax({
                        url: 'http://localhost:8080/Appointment/getDoctors?departmentId=' + departmentId,
                        method: 'GET',
                        success: function(data) {
                            data.forEach(function(doctor) {
                                $('#two').append(new Option(doctor.name, doctor.id));
                            });
                        }
                    });
                }
            });
        });
    </script>
    </body>
</html>