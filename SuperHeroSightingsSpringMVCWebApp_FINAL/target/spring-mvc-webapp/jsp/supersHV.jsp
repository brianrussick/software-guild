<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
        <title>Superheros & Supervillains</title>
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
            <li class="nav-item "><a class="navbar-text" href="${pageContext.request.contextPath}/superSightings"><b>View Sightings</b></a>
            </li>
            <li class="nav-item" class="active"><a class="navbar-text" href="${pageContext.request.contextPath}/supersHV"><b>Superheros & Supervillains</b></a>
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
        <div id="header" style="text-align:center;">
            <h2 class="font-weight-bold text-uppercase">Super Hero & Super Villain Sightings Web App</h2>
            <hr/>
        </div>
        <div class='row'>
        <div class='col-lg-5'>
            <h3 class="font-weight-bold text-uppercase" style="text-align:center;">Superheros | Supervillains</h3>
            <br>
            <table id='superTable' class='table table-hover' style="text-align: center">
            <thead class="thead-dark">
            <tr>
            <th style="text-align: center">Name</th>
            <th style="text-align: center">Image</th>
            <th style="text-align: center">Edit</th>
            <th style="text-align: center">Delete</th>
            </tr>
            </thead>
            <c:forEach var="superHVCurrent" items="${superHVList}">
            <tr>
            <td>
            <a href="superHVInfo?superHVId=${superHVCurrent.id}">
            <c:out value="${superHVCurrent.name}"/>
            </a>
            </td>
            <td>
            <a href="${superHVCurrent.image}">
            <img width="25" height="25" src="<c:url value="${superHVCurrent.image}"/>"/>
            </a>
            </td>
            <td>
            <a href="displayEditSuper?superHVId=${superHVCurrent.id}">
            Edit
            </a>
            </td>
            <td>
            <a href="deleteSuper?superHVId=${superHVCurrent.id}">
            Delete
            </a>
            </td>
            </tr>
            </c:forEach>
            </table>
        </div>
        <div class='col-lg-6'>
            <h3 class="font-weight-bold text-uppercase" style="text-align:center;">ADD Super Hero OR Villain</h3>
            <br>
            <form class='form-horizontal' role='form' method='POST' enctype="multipart/form-data" action='createSuper'>
        <div class='form-group'>
            <label for='name' class='col-lg-3 control-label'>Name:</label>
        <div class='col-lg-6'>
            <input type='text' class='form-control' name='name' Placeholder='Name' required/>
        </div>
        </div>
        <div class='form-group'>
            <label for='description' class='col-lg-3 control-label'>Description:</label>
        <div class='col-lg-6'>
            <input type='text' class='form-control' name='description' Placeholder='Description'/>
        </div>
        </div>
        <div class='form-group'>
            <label for='image' class='col-lg-3 control-label'>Image:</label>
        <div class='col-lg-6'>
            <input type='file' class='form-control' name='image' Placeholder='Image'/>
        </div>
        </div>
        <div class="form-group">
            <label for="organizationIdList" class="col-lg-3 control-label">Organizations:</label>
        <div class="col-lg-6">
            <select multiple id="organizationIdList"
                    name="organizationIdList"
                    class="form-control">
            <c:forEach items="${orgList}" var="currentOrganizationList">
            <option value="${currentOrganizationList.id}">
            <c:out value="${currentOrganizationList.name}"/>
            </option>
            </c:forEach>
            </select>
        </div>
        </div>
        <div class="form-group">
            <label for="powerIdList" class="col-lg-3 control-label">Super Powers:</label>
        <div class="col-lg-6">
            <select multiple id="powerIdList"
                           name="powerIdList"
                           class="form-control">
            <c:forEach items="${superPowerList}" var="currentSuperPower">
            <option value="${currentSuperPower.id}">
            <c:out value="${currentSuperPower.name}"/>
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
        </div>
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>