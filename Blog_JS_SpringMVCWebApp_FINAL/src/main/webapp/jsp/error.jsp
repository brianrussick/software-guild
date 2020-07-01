<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error Page</title>
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
    </div>
        <br><br>
        <center><blockquote>
    <div>
        <h1>Error!</h1>
        <h3>${errorMessage}</h3><center></blockquote>
    </div>
    </div>  
<script src='js/navTabs.js'></script>
</body>
</html>