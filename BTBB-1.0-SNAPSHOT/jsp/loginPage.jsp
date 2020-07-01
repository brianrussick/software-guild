<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BTBB Login Page</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/bootstrap-grid.css" rel="stylesheet">
</head>
    <style>
        .navbar{
            float: right;
            margin-top: -60px;
        }
    </style>
        <body style="background-color: white; color: #337ab7; text-shadow: none">
    <div>
        <h1 style="margin-bottom: 12px; font-family: fantasy;">BENNY THE BAKER BLOG</h1>
    <div id='navTabs' class='navbar' style="text-shadow: none">
    </div>
        <hr style="color: #337ab7; margin-top: -12px;">
    </div>
    <div class="container">
        <c:if test="${param.login_error == 1}">
            <h3>Wrong id or password!</h3>
        </c:if>
    <div class="row">
    <div class="col-md-6" style="padding: 40px; margin: 10%;margin-left: 55%; background-color: #F5F5F5">
        <h1>Login</h1>
    <form class="form-horizontal pull-left"
          designationRole="form"
          method="Post"
          action="j_spring_security_check">
    <div class="form-group">
        <label for="j_username" class="col-md-4 control-label">Username:</label>
    <div class="col-md-8">
        <input type="text" class="form-control" name="j_username" placeholder="Username"/>
    </div>
    </div>
    <div class="form-group">
        <label for="j_password" class="col-md-4 control-label">Password:</label>
    <div class="col-md-8">
        <input type="password" class="form-control" name="j_password" placeholder="Password"/>
    </div>
    </div>
    <div class="form-group">
    <div class="col-md-offset-4 col-md-8">
        <input type="submit" class="btn btn-primary" id="search-button" value="Sign In"/>
    </div>
    </div>
    </form>
    </div>
    </div>
    </div>
<script src='js/navTabs.js'></script>
</body>
</html>