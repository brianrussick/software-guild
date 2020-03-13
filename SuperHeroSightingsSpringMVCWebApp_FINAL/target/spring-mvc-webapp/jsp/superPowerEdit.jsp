<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
        <title>Edit Super Powers</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap4.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body style="background-color: #f9f9f9">
        <nav class="navbar navbar-expand-sm navbar-dark bg-dark">
        <div style="height: 40px;float: left" ;width:100%>
            <p class="nav-item"><a class="navbar-text" style="width: 100%; line-height: 0px;margin-top: 12px" 
                href="${pageContext.request.contextPath}/superPowers"><b>Go Back To Super Powers</b></a></p>
        </div>
        </nav>
        <div class="container">
        <div id="header" style="text-align:center;">
            <h2 class="font-weight-bold text-uppercase" style="text-align: center">Super Hero & Super Villain Sightings Web App</h2>
            <hr/>
        </div>
            <h3 class="font-weight-bold text-uppercase text-muted">Edit Super Powers</h3><br>
            <sf:form class="form-horizontal" role="form" modelAttribute="Power" action="superPowerEdit" method="POST">
            <sf:input type="hidden" id="id" path="id"/>
        <div class="form-group">
            <label for="name" class="col-lg-1 control">Name: </label>
        <div class="col-lg-5">
            <sf:input type="text" class="form-control" id="name" path="name" placeholder="Name"/>
            <sf:errors path="name" cssClass="error"></sf:errors>
        </div>
        </div>
        <div class="form-group">
            <label for="description" class="col-lg-1 control">Description: </label>
        <div class="col-lg-5">
            <sf:input type="text" class="form-control" id="description" path="description" placeholder="Description"/>
            <sf:errors path="description" cssClass="error"></sf:errors>
        </div>
        </div>
        <div class="form-group">
            <label for="superHVIdList" class="col-lg-1 control">Super_H/V: </label>
        <div class="col-lg-5">
            <sf:select id="superHVIdList"
                     path="superHVIdList"
                 multiple="multiple"
                    class="form-control">
            <c:forEach items="${superHVList}" var="currentSuperHV">
            <option value="${currentSuperHV.id}">
            <c:out value="${currentSuperHV.name}"/>
            </option>
            </c:forEach>
            </sf:select>
        </div>
            <br>
        <div>
            <br>
        <div class="col-lg-offset-1 col-lg-6"><br>
            <input type="submit" class="btn btn-primary" value="UPDATE"/>
        </div>
        </div>
            </sf:form>
        </div>
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>