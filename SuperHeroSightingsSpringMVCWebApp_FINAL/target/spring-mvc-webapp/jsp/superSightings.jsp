<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
        <title>View Sightings</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap4.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body style="background-color: #f9f9f9">
        <div class="card" style="background-color: #f9f9f9; height: 100%">
        <nav class="navbar navbar-expand-sm navbar-dark bg-dark">
        <div class="container" style="height: 5px">
            <ul class="nav navbar-nav">
            <li class="nav-item "><a class="navbar-text" href="${pageContext.request.contextPath}/"><b>Home</b></a>
            </li>
            <li class="nav-item " class="active"><a class="navbar-text" href="${pageContext.request.contextPath}/superSightings"><b>View Sightings</b></a>
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
        <div class='row'>
        <div class='col-lg-5'>
            <h3 class="font-weight-bold text-uppercase" style="text-align:center;">Sightings</h3><br>
            <table id='sightingTable' class='table table-hover' style="text-align: center">
            <thead class="thead-dark">
            <tr>
            <th style="text-align: center">Date</th>
            <th style="text-align: center">Edit</th>
            <th style="text-align: center">Delete</th>
            </tr>
            </thead>
            <c:forEach var="currentSighting" items="${superSightingList}">
            <tr>
            <td>
            <a href="superSightingInfo?sightingId=${currentSighting.id}">
            <c:out value="${currentSighting.date}"/>
            </a>
            </td>
            <td>
            <a href="displayEditSighting?sightingId=${currentSighting.id}">
            Edit
            </a>
            </td>
            <td>
            <a href="deleteSighting?sightingId=${currentSighting.id}">
            Delete
            </a>
            </td>
            </tr>
            </c:forEach>
            </table>
        </div>
        <div class='col-lg-5'>
            <h3 class="font-weight-bold text-uppercase" style="text-align:center;">Add Sightings</h3><br>
            <form class='form-horizontal' role='form' method='POST' action='createSighting'>
        <div class='form-group'>
            <label for='date' class='col-lg-3 control-label'>Date:</label>
        <div class='col-lg-6'>
            <input type='date' class='form-control' name='date' required/>
        </div>
        </div>
        <div class="form-group">
            <label for="superHVId" class="col-lg-3 control-label">Super H/V:</label>
        <div class="col-lg-6">
            <select id="superHVId"
                  name="superHVId"
                 class="form-control">
            <c:forEach items="${superHVList}" var="superHVCurrent">
            <option value="${superHVCurrent.id}">
            <c:out value="${superHVCurrent.name}"/>
            </option>
            </c:forEach>
            </select>
        </div>
        </div>
        <div class="form-group">
            <label for="locationId" class="col-lg-3 control-label">Location:</label>
        <div class="col-lg-6">
            <select id="locationId"
                  name="locationId"
                 class="form-control">
            <c:forEach items="${locaList}" var="currentLocationList">
            <option value="${currentLocationList.id}">
            <c:out value="${currentLocationList.name}"/>
            </option>
            </c:forEach>
            </select>
        </div>
        </div>
        <div class='form-group'>
        <div class='col-lg-offset-3 col-lg-6'>
            <input type='submit' class='btn btn-primary' value='CREATE'/>
        </div>
        </div>
            </form>
        </div>
        </div>
        </div>
            <br>
            <br>
            <br>
        </div>
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>