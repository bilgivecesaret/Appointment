<%@ page language="java" contentType="text/html; charset=ISO-8859-9" pageEncoding="ISO-8859-9"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-9">
        <title>APPOINTMENT SYSTEM</title>
        <%@include file="../component/allcss.jsp" %>
        <style type="text/css">
            .paint-card {
                box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
            }
        </style>
    </head>
    <body>
        <% 
            Patient p = (Patient) session.getAttribute("userObj");
            if(p==null){
                response.sendRedirect("http://localhost:8080/Appointment/index.jsp");
            }
        %>
        <%@include file="navbar.jsp" %>
        <%@include file="../component/doctorCards.jsp" %> 
        <%@include file="../component/footer.jsp" %>
    </body>
</html>