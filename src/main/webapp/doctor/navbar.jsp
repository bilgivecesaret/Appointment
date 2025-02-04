<%@page import="entity.Doctor"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-success">
    <div class="container-fluid">
        <a class="navbar-brand" href="index.jsp"><i
                class="fas fa-clinic-medical"></i> MEDI HOME </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
            <a  href="http://localhost:8080/Appointment/index.jsp"></a>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
            <% 
                Doctor dr = (Doctor) session.getAttribute("doctor");
                if(dr==null){
            %>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" 
                       href="http://localhost:8080/Appointment/admin/adminLogin.jsp">ADMIN</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" 
                       href="http://localhost:8080/Appointment/doctor/doctorLogin.jsp">DOCTOR</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" 
                       href="http://localhost:8080/Appointment/patient/patientLogin.jsp">PATIENT</a>
                </li>
            <%}else{%>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" 
                        href="http://localhost:8080/Appointment/showAppointments">SHOW APPOINTMENT</a>
                </li>
            <%}%>
                <div class="dropdown">
                    <button class="btn btn-success dropdown-toggle" type="button"
                            id="dropdownMenuButton1" data-bs-toggle="dropdown"
                            aria-expanded="false">
                        <i class="fa-solid fa-circle-user"></i> ${doctor.getFullname()}
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                        <li><a class="dropdown-item" 
                               href="http://localhost:8080/Appointment/doctorLogout">
                                Logout</a></li>

                    </ul>
                </div>
            </ul>
        </div>
    </div>
</nav>