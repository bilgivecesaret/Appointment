<%@page import="java.util.Date"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<%@page import="entity.Doctor"%>
<%@page import="entity.Department"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-9" pageEncoding="ISO-8859-9"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-9">
        <title>Update Appointment</title>
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
        <%@include file="./navbar.jsp"%>
        <div class="container-fulid backImg p-5">
            <p class="text-center fs-2 text-white"></p>
        </div>
        <div class="container p-3">
            <div class="row">
                <div class="col-md-6 p-5">
                    <img alt="" src="../img/doctor1.jpg" height="500px" width="450px">
                </div>
                <div class="col-md-6">
                    <div class="card paint-card">
                        <div class="card-body">
                            <p class="text-center fs-3">Update Appointment</p>
                                <c:if test="${not empty errorMsg}">
                                        <p class="fs-4 text-center text-danger">${errorMsg}</p>
                                        <c:remove var="errorMsg" scope="session" />
                                </c:if>
                                <c:if test="${not empty succMsg}">
                                        <p class=" fs-4 text-center text-success">${succMsg}</p>
                                        <c:remove var="succMsg" scope="session" />
                                </c:if>
                                <form class="row g-3" action="http://localhost:8080/Appointment/updateAppointment" method="post">                                    
                                    <div class="col-md-6">
                                        <label for="departmentId" class="form-label">Department</label> 
                                        <select disabled class="form-control" name="departmentId" id="one" onchange="showSecondDropdown()">
                                            <option value="">--select--</option>
                                        </select>
                                    </div>
                                    <div class="col-md-6">
                                        <label for="appoint_date" class="form-label">Appointment Date</label> 
                                        <input type="date" class="form-control" required name="appoint_date" value="${param.appointDate}">
                                    </div>
                                    <div class="col-md-6">
                                        <label for="appoint_time" class="form-label">Appointment Time</label>
                                        <select class="form-control" name="appoint_time" required>
                                            <option value="">--Select Time--</option>
                                            <option value="09:00:00" ${param.appointTime == '09:00:00' ? 'selected' : ''}>09:00</option>
                                            <option value="09:30:00" ${param.appointTime == '09:30:00' ? 'selected' : ''}>09:30</option>
                                            <option value="10:00:00" ${param.appointTime == '10:00:00' ? 'selected' : ''}>10:00</option>
                                            <option value="10:30:00" ${param.appointTime == '10:30:00' ? 'selected' : ''}>10:30</option>
                                            <option value="11:00:00" ${param.appointTime == '11:00:00' ? 'selected' : ''}>11:00</option>
                                            <option value="11:30:00" ${param.appointTime == '11:30:00' ? 'selected' : ''}>11:30</option>
                                            <option value="13:30:00" ${param.appointTime == '13:30:00' ? 'selected' : ''}>13:30</option>
                                            <option value="14:00:00" ${param.appointTime == '14:00:00' ? 'selected' : ''}>14:00</option>
                                            <option value="14:30:00" ${param.appointTime == '14:30:00' ? 'selected' : ''}>14:30</option>
                                            <option value="15:00:00" ${param.appointTime == '15:00:00' ? 'selected' : ''}>15:00</option>
                                            <option value="15:30:00" ${param.appointTime == '15:30:00' ? 'selected' : ''}>15:30</option>
                                            <option value="16:00:00" ${param.appointTime == '16:00:00' ? 'selected' : ''}>16:00</option>
                                            <option value="16:30:00" ${param.appointTime == '16:30:00' ? 'selected' : ''}>16:30</option>                                            
                                        </select>
                                    </div>    
                                    <div class="col-md-6">
                                        <label for="doctorId" class="form-label">Doctor</label> 
                                        <select required class="form-control" name="doctorId" id="two">
                                            <option value="">--select--</option>
                                        </select>
                                    </div>
                                    <button type="submit" class="col-md-6 offset-md-3 btn btn-success">Update</button>                                    
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
                    $('#one').val('${param.departmentId}'); // Set the department value after loading
                    if ($('#one').val()) {
                        showSecondDropdown();
                        loadDoctors($('#one').val(), '${param.getDoctorId}');
                    }
                }
            });

            // Departman seçildiğinde doktorları yükle
            $('#one').on('change', function() {
                var departmentId = $(this).val();
                loadDoctors(departmentId);
            });
        });

        function loadDoctors(departmentId, selectedDoctorId) {
            $('#two').empty().append(new Option("---Select---", ""));
            if (departmentId) {
                $.ajax({
                    url: 'http://localhost:8080/Appointment/getDoctors?departmentId=' + departmentId,
                    method: 'GET',
                    success: function(data) {
                        data.forEach(function(doctor) {
                            $('#two').append(new Option(doctor.name, doctor.id));
                        });
                        if (selectedDoctorId) {
                            $('#two').val(selectedDoctorId); // Set the doctor value after loading
                        }
                    }
                });
            }
        }
    </script>
    </body>
</html>
