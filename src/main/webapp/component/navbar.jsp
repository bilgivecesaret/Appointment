<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-success">
    <div class="container-fluid">
        <a class="navbar-brand" href="http://localhost:8080/Appointment/index.jsp">
            <i class="fas fa-clinic-medical"></i> MEDI HOME </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" 
                       href="http://localhost:8080/Appointment/admin/admin_login.jsp">
                        <i class="fas fa-sign-in-alt"></i>ADMIN</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" 
                       href="http://localhost:8080/Appointment/doctor/doctor_login.jsp">DOCTOR</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" 
                        href="http://localhost:8080/Appointment/patient/patientLogin.jsp">PATIENT</a>
                </li>
            </ul>
        </div>
    </div>
</nav>