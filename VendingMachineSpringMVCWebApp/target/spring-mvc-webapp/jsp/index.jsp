<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
        <title>Vending Machine</title>
        
        <!-- bootstrap css link -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
</head>
<body>
        
        <!-- overwrites the default background style applied by bootstrap -->
        <style type="text/css">
        body {background:#40E0D0!important;}
        </style>
        <div class="container">
            <h1 class="text-center">Vending Machine</h1>
            <hr/>
        <div class="col-lg-9">
            <sf:form class="form-horizontal" role="form" method="POST" action="calculate" modelAttribute="userSelection" > 
                
            <!-- adds snacks with divs equally spaced 3 x 3 boxes -->
            <ul id="snacks" style="list-style-type:none;margin:0px;">         
            <c:forEach var="currentSnack" items="${snackList}">
            <li>
            <a style="text-decoration: none; color: black" href="selectSnack?snackIdNum=${currentSnack.snackIdNum}&userTotal=${userSelection.userTotal}">
        <div class="col-lg-3" style="border: 15px solid black; margin: 4%">
            <p><c:out value="${currentSnack.snackIdNum}"/>
            </p>
            <p style="text-align: center">
            <c:out value="${currentSnack.snackName}"/>
            </p>
            <p style="text-align:center;">
            $<c:out value="${currentSnack.snackCost}"/>
            </p>
            <br>
            <br>
            <p style="text-align:center;">
            Quantity Left:  <c:out value="${currentSnack.snackStockNum}"/>
            </p>
        </div> 
            </a> 
            </li>
            </c:forEach>
            </ul>
        </div>

        <!-- sets formatting for the right side panel -->
        <div class="col-lg-3">

        <!-- Total $ In section -->
        <div class="row">
            <h2 class="text-center">Total $ In</h2>
        <div class="row form-group">
        <div class="col-lg-6 col-lg-offset-3">
            <sf:input type="text" class="form-control" path="userTotal" readonly="true"/>                          
            <sf:errors path="userTotal" cssclass="error"></sf:errors>
        </div>
        </div>   
        <div class="row form-group">
        <div class="col-lg-5">
            <input type="submit" 
                   role="button" 
                   class="btn btn-default" 
                   id="dollar-button" 
                   name="dollarButton" 
                   value="Add Dollar"/>
            </div>
        <div class="col-lg-5 col-lg-offset-2">
            <input type="submit" 
                   role="button" 
                   class="btn btn-default" 
                   id="quarter-button" 
                   name="quarterButton" 
                   value="Add Quarter"/>
            </div>
        </div>
        <div class="row form-group">
        <div class="col-lg-5">
            <input type="submit" 
                   role="button" 
                   class="btn btn-default" 
                   id="dime-button" 
                   name="dimeButton" 
                   value="Add Dime"/>
            </div>
        <div class="col-lg-5 col-lg-offset-2">
            <input type="submit" 
                   role="button" 
                   class="btn btn-default" 
                   id="nickel-button" 
                   name="nickelButton" 
                   value="Add Nickel"/>
            </div>
        </div>
            <hr/>
            
        <!-- purchase snacks & msgs form-->          
        <div class="row">
            <h2 class="text-center">Messages</h2>
        <div class="row form-group">
        <div class="col-lg-10 col-lg-offset-2">
            <sf:input type="text" class="form-control " path="message" />
            <sf:errors path ="message" cssclass="error"></sf:errors>
        </div>
        </div>
        <div class="row form-group">
            <label for="snack-selected" class="control-label col-lg-2">
            <h3 style="display:inline">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Item:</h3></label>
        <div class="col-lg-7 col-lg-offset-3">
            <sf:input type="text" class="form-control" value="${snack.snackIdNum}" path="snackChoice"/>
            <sf:errors path="snackChoice" cssclass="error"></sf:errors>
        </div>
        </div>
        <div class="row form-group">
            <input type="submit" 
                   class="btn btn-default col-lg-8 col-lg-offset-2" 
                   role="button" 
                   value="Make Purchase" 
                   name="purchaseSnack"/>
            </div>
        </div>
            <hr/>

        <!-- change display & form -->
        <div class="row">
            <h2 class="text-center"> Change</h2>
        <div class="row form-group">
        <div class="col-lg-10 col-lg-offset-1">
            <sf:textarea class="form-control" id="snacks-change-display" path="change"></sf:textarea>
        </div>
        </div>
        <div class="row form-group">
            <input type="submit" 
                   class="btn btn-default col-lg-offset-3 col-lg-6" 
                   role="button" 
                   value="Return Change" 
                   name="snacksChgRtn" 
                   id="snacks-change-return"/>
            </div>
                </sf:form>
            </div
            </div>
            </div>

    <!-- scripts to run -->
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>