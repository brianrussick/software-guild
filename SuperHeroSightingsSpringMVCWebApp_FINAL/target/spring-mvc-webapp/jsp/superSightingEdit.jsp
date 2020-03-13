<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
        <title>Edit Sighting</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap4.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body style="background-color: #f9f9f9">
        <nav class="navbar navbar-expand-sm navbar-dark bg-dark">
        <div style="height: 40px;float: left" ;width:100%>
            <p class="nav-item"><a class="navbar-text" style="width: 100%; line-height: 0px;margin-top: 12px" 
                href="${pageContext.request.contextPath}/superSightings"><b>Go Back To Sightings</b></a></p>
        </div>
        </nav>
        <div class="container">
        <div id="header" style="text-align:center;">
            <h2 class="font-weight-bold text-uppercase" style="text-align:center;">Super Hero & Super Villain Sightings Web App</h2>
            <hr/>
        </div>
            <h3 class="font-weight-bold text-uppercase text-muted">Edit Sighting</h3>
            <br>
            <form class="form-horizontal" role="form" action="superSightingEdit" method="POST">
            <input type="hidden" id="id" class='form-control' path="id" name="id" value="${sighting.id}"/>
        <div class='form-group'>
            <label for='date' class='col-lg-1 control'>Date:</label>
        <div class='col-lg-3'>
            <input type='date' class='form-control' name='date' id="date" value="${sighting.date}" path="date" required/>
        </div>
        </div>
        <div class="form-group">
            <label for="superHVId" class="col-lg-1 control">Super_H/V:</label>
        <div class="col-lg-3">
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
            <label for="locationId" class="col-lg-1 control">Location:</label>
        <div class="col-lg-3">
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
        <div class="form-group">
        <div class="col-lg-offset-1 col-lg-6">
            <input type="submit" class="btn btn-primary" value="UPDATE"/>
        </div>
        </div>
            </form>
        </div>
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>