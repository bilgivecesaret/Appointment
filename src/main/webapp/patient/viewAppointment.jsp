<%@page import="entity.Department"%>
<%@page import="java.util.ArrayList"%>
<%@page import="javax.persistence.Query"%>
<%@page import="javax.persistence.EntityManager"%>
<%@page import="javax.persistence.Persistence"%>
<%@page import="javax.persistence.EntityManagerFactory"%>
<%@page import="entity.Patient"%>
<%@page import="entity.Doctor"%>
<%@page import="entity.Appointment"%>
<%@page import="java.util.List"%>
<%@page import="dao.AppointmentDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>Your All Appointments</title>
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
	<c:if test="${empty userObj }">
            <c:redirect url="http://localhost:8080/Appointment/patient/patientLogin.jsp"></c:redirect>
	</c:if>
	<%@include file="./navbar.jsp"%>
	<div class="container-fulid backImg p-5">
            <p class="text-center fs-2 text-white"></p>
	</div>
	<div class="container p-3">
            <div class="row">
                <div class="col-md-9">
                    <div class="card paint-card">
                        <div class="card-body">
                            <p class="fs-4 fw-bold text-center text-success">Appointment List</p>
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th scope="col">Appoint Date</th>
                                        <th scope="col">Appointment Time</th>
                                        <th scope="col">Department</th>
                                        <th scope="col">Doctor</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                    EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
                                    EntityManager em = emf.createEntityManager(); 
                                    Patient patient = (Patient) session.getAttribute("userObj");
                                    List<Appointment> list = new ArrayList<>();
                                    Query query = em.createQuery("SELECT c FROM Appointment c WHERE c.patientId = :patientId");
                                    query.setParameter("patientId", patient.getId());
                                    list = query.getResultList();
                                    for (Appointment ap : list) {
                                        Query query2 = em.createQuery("SELECT c FROM Doctor c WHERE c.doctorId = :doctorId");
                                        query.setParameter("doctorId", ap.getDoctorId());
                                        Doctor d = (Doctor) query.getSingleResult();
                                        Query query3 = em.createQuery("SELECT c FROM Department c WHERE c.departmentId = :departmentId");
                                        query3.setParameter("departmentId", d.getDepartmentId());
                                        Department dp = (Department) query.getSingleResult();
                                    %>
                                    <tr>
                                        <th><%=ap.getAppointDate()%></th>
                                        <td><%=ap.getAppointmentTime()%></td>
                                        <td><%=dp.getName()%></td>
                                        <td><%=d.getFullname()%></td>
                                    </tr>
                                    <%
                                    }
                                    %>
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