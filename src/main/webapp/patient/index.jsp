<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
    <title>APPOINTMENT SYSTEM</title>
    <%@include file="../component/allcss.jsp" %>

    <style type="text/css">
        .paint-card {
            box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.3);
        }
    </style>

</head>
<body>

    <%@include file="navbar.jsp" %> 
<div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
    <div class="carousel-indicators">
        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
        <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
    </div>
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img src="./img/background1.jpg" class="d-block w-100" alt="..." height="600px">
        </div>
        <div class="carousel-item">
            <img src="./img/background2.jpg" class="d-block w-100" alt="..." height="600px">
        </div>
        <div class="carousel-item">
            <img src="./img/background3.jpg" class="d-block w-100" alt="..." height="600px">
        </div>
        <div class="carousel-item">
            <img src="./img/background4.jpg" class="d-block w-100" alt="..." height="600px">
        </div>
    </div>
    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
    </button>
    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
    </button>
</div>


<div class="container p-2">
    <p class="text-center fs-2 ">Our Team</p>

    <div class="row">
        <div class="col-md-3">
            <div class="card paint-card">
                <div class="card-body text-center">
                    <img src="img/doct7.jpg" width="250px" height="300px">
                        <p class="fw-bold fs-5">Dr Samuani Simi</p>
                        <p class="fs-7">(CEO & Chairman)</p>
                </div>
            </div>
        </div>

        <div class="col-md-3">
            <div class="card paint-card">
                <div class="card-body text-center">
                    <img src="img/doct8.jpg" width="250px" height="300px">
                        <p class="fw-bold fs-5">Dr. Julie Cronk</p>
                        <p class="fs-7">(Chief Doctor)</p>
                </div>
            </div>
        </div>

        <div class="col-md-3">
            <div class="card paint-card">
                <div class="card-body text-center">
                    <img src="img/doct9.jpg" width="250px" height="300px">
                        <p class="fw-bold fs-5">Dr. Nensa Paule</p>
                        <p class="fs-7">(Chief Doctor)</p>
                </div>
            </div>
        </div>

        <div class="col-md-3">
            <div class="card paint-card">
                <div class="card-body text-center">
                    <img src="img/doct10.jpg" width="250px" height="300px">
                        <p class="fw-bold fs-5">Dr. Mathey Samuel</p>
                        <p class="fs-7">(Chief Doctor)</p>
                </div>
            </div>
        </div>

    </div>

</div>

<%@include file="../component/footer.jsp" %>

</body>
</html>