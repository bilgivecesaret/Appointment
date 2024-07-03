<%@ page language="java" contentType="text/html"%>
<!DOCTYPE html>
<html>
    <head>
        <title>APPOINTMENT SYSTEM</title>
        <%@include file="../component/allcss.jsp" %>
        <style type="text/css">
            .paint-card {
                box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
            }
        </style>
    </head>
    <body>
        <c:if test="${ empty userObj }">
            <c:redirect url="http://localhost:8080/Appointment/index.jsp"></c:redirect>
        </c:if>
        <%@include file="navbar.jsp" %>
        <%@include file="../component/doctorCards.jsp" %> 
        <%@include file="../component/footer.jsp" %>
    </body>
</html>