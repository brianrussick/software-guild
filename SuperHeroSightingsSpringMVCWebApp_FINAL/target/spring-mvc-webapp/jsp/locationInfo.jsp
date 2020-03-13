<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
        <title>Location Information</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap4.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body style="background-color: #f9f9f9">
        <nav class="navbar navbar-expand-sm navbar-dark bg-dark">
        <div style="height: 40px;float: left" ;width:100%>
            <p class="nav-item"><a class="navbar-text" style="width: 100%; line-height: 0px;margin-top: 12px" 
                href="${pageContext.request.contextPath}/locations"><b>Go Back To Locations</b></a>
            </p>
        </div>
        </nav>
        <div class="container">
        <div id="header" style="text-align:center;">
            <h2 class="font-weight-bold text-uppercase">Super Hero & Super Villain Sightings Web App</h2>
            <hr/><br><br><br>
        </div>            
            <h3 class="font-weight-bold text-uppercase text-muted">Location Information</h3>      
            <h2 class="text-muted" style="text-align:left;">
            -------------------------------------<br>
            <b>Name: </b>
            <c:out value="${location.name}"/><br>
            <b>Description: </b>
            <c:out value="${location.description}"/><br>
            <b>Address: </b>
            <c:out value="${location.address}"/><br>
            <b>Latitude: </b>
            <c:out value="${location.latitude}"/><br>
            <b>Longitude: </b>
            <c:out value="${location.longitude}"/><br>
            -------------------------------------
            </h2>
        </div>
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>