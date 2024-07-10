<%@ page import="entity.Department" %>
<%@ page import="javax.persistence.EntityManager" %>
<%@ page import="javax.persistence.EntityManagerFactory" %>
<%@ page import="javax.persistence.Persistence" %>
<%@ page import="java.util.List" %>
<<<<<<< Updated upstream
=======
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page isELIgnored="false"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-9" pageEncoding="ISO-8859-9"%>
>>>>>>> Stashed changes
<!DOCTYPE html>
<html>
<head>
        <meta charset="ISO-8859-9">
        <title>Add Doctor</title>
        <%@include file="../component/allcss.jsp"%>
        <style type="text/css">
            .paint-card {
                    box-shadow: 0 0 8px 0 rgba(0, 0, 0, 0.3);
            }

            .backImg {
                    background: linear-gradient(rgba(0, 0, 0, .4), rgba(0, 0, 0, .4)),
                            url("../img/background2.jpg");
                    height: 40vh;
                    width: 100%;
                    object-fit: fill;
                    background-repeat: no-repeat;
            }
        </style>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>
<body>
<<<<<<< Updated upstream
    <h2>Add Doctor</h2>
    <form action="http://localhost:8080/Appointment/addDoctor" method="post">
        <label for="name">Full Name:</label>
        <input type="text" id="name" name="name" required><br>

        <label for="username">Username:</label>
        <input type="text" id="username" name="username" required><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br>

        <label for="department">Select Department:</label>
        <select id="department" name="department" required>
            <%
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
                EntityManager em = emf.createEntityManager();
                List<Department> departments = em.createQuery("SELECT d FROM Department d", Department.class).getResultList();
                em.close();
                emf.close();

                for (Department department : departments) {
            %>
                <option value="<%= department.getId() %>"><%= department.getName() %></option>
            <%
                }
            %>
        </select>
        <br>
        <input type="submit" value="Add Doctor">
    </form>
=======
    <c:if test="${ empty admin }">
        <c:redirect url="http://localhost:8080/Appointment/admin/adminLogin.jsp"></c:redirect>
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
                        <p class="text-center fs-3">Add Doctor</p>
                            <c:if test="${not empty errorMsg}">
                                    <p class="fs-4 text-center text-danger">${errorMsg}</p>
                                    <c:remove var="errorMsg" scope="session" />
                            </c:if>
                            <c:if test="${not empty succMsg}">
                                    <p class=" fs-4 text-center text-success">${succMsg}</p>
                                    <c:remove var="succMsg" scope="session" />
                            </c:if>
                            <form class="row g-3" action="http://localhost:8080/Appointment/addDoctor" method="post">
                                <div class="col-md-6">
                                    <label for="name" class="form-label">Fullname</label> 
                                    <input type="text" class="form-control" name="name" required><br>
                                </div>
                                <div class="col-md-6">
                                    <label for="username" class="form-label">Username</label> 
                                    <input type="text" class="form-control" required name="username">
                                </div>
                                <div class="col-md-6">
                                    <label for="password" class="form-label">Password</label> 
                                    <input type="text" class="form-control" required name="password">
                                </div>
                                <div class="col-md-6">
                                    <label for="department" class="form-label">Select Department</label>
                                    <select class="form-control" id="department" name="department" required>
                                        <%
                                            EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
                                            EntityManager em = emf.createEntityManager();
                                            List<Department> departments = em.createQuery("SELECT d FROM Department d", Department.class).getResultList();
                                            em.close();
                                            emf.close();

                                            for (Department department : departments) {
                                        %>
                                            <option value="<%= department.getId() %>"><%= department.getName() %></option>
                                        <%
                                            }
                                        %>
                                    </select>
                                </div>
                                <button type="submit" class="col-md-6 offset-md-3 btn btn-success">Save</button>                                    
                            </form>
                            <div class="row g-3" style="margin-top: 10px">
                                <a href="http://localhost:8080/Appointment/admin/addDoctor.jsp" >
                                        <button class="col-md-6 offset-md-3 btn btn-success">Clear</button>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
        </div>
    </div>
    <%@include file="../component/footer.jsp"%>
>>>>>>> Stashed changes
</body>
</html>



    
        
        