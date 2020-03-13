<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
        <title>Home Page</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap4.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body style="background-color: #f9f9f9">
        <div class="card" style="background-color: #f9f9f9; height: 100%">
        <nav class="navbar navbar-expand-sm navbar-dark bg-dark">
        <div class="container" style="height: 5px">
            <ul class="nav navbar-nav">
            <li class="nav-item ">
            <a class="navbar-text" href="${pageContext.request.contextPath}/"><b>Home</b></a>
            </li>
            <li class="nav-item ">
            <a class="navbar-text" href="${pageContext.request.contextPath}/superSightings"><b>View Sightings</b></a>
            </li>
            <li class="nav-item"><a class="navbar-text" href="${pageContext.request.contextPath}/supersHV"><b>Superheros & Supervillains</b></a>
            </li>
            <li class="nav-item"><a class="navbar-text" class="nav-link" href="${pageContext.request.contextPath}/superPowers"><b>Super Powers</b></a>
            </li>
            <li class="nav-item"><a class="navbar-text" href="${pageContext.request.contextPath}/orgs"><b>Organizations</b></a>
            </li>
            <li class="nav-item"><a class="navbar-text" href="${pageContext.request.contextPath}/locations"><b>Locations</b></a>
            </li>
            </ul>
        </div>
        </nav>
        <div class="container">
        <div id="header" style="text-align:left;">
            <h2 class="font-weight-bold text-uppercase" style="text-align:center;">Super Hero & Super Villain Sightings Web App</h2>
            <hr/>
        </div>
        <div id="main">
        <div id='content'>
        <div class="row">
        <div class="col-lg-5">
            <h4 class="text-muted">
            <blockquote>
            ------ PLEASE READ & HELP OUR EFFORTS ------<br><br>
            <b>With the rising popularity of superhero movies there has been a heightened awareness of
            super heroes in our midst. The frequency of super hero (and super villain) sightings is
            increasing at an alarming rate. This "super" management system will help 
            to track down all of the super heros & villains in the world.</b><br><br>
            ------------- We appreciate any assistance! -------------
            </blockquote>
            </h4>
        </div>
        <div class="col-lg-5">
            <h3 class="font-weight-bold text-uppercase" style="text-align:center;">10 Most Recent Sightings List</h3><br>
            <table class='table table-hover' style="text-align:center">
            <thead class="thead-dark">
            <th style="text-align: center">Sighting Date</th>
            <th style="text-align: center">Super H/V</th>
            <th style="text-align: center">Image</th>
            </thead>
            <tbody>
            <c:forEach var="currentSightingList" items="${sightingList}">
            <tr>
            <td>${currentSightingList.date}</td>
            <td>${currentSightingList.heroName}</td>
            <td><img width="30" height="30" src=" <c:url value="${currentSightingList.heroImage}"/>"/></td>
            </tr>
            </c:forEach>
            </tbody>
            </table>
        </div>
        </div>
        </div>
        </div>
        </div>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
            <br>
        </div>
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>