<%--
  Created by IntelliJ IDEA.
  User: stefan.pamparau
  Date: 5/3/2016
  Time: 3:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link rel="stylesheet"
          href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>

<jsp:include page="../../../../fragments/navigationBar.jsp"/>

<div class="container">
    <form action="/clientAccounts/insert" method="POST" class="form-horizontal">
        <div class="form-group">
            <label class="control-label col-sm-2" for="emailField">Email:</label>
            <div class="col-sm-10">
                <input id="emailField" name="email" type="email" class="form-control"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="passwordField">Password:</label>
            <div class="col-sm-10">
                <input id="passwordField" name="password" type="password" class="form-control"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="balanceField">Balance:</label>
            <div class="col-sm-10">
                <input id="balanceField" name="balance" type="number" class="form-control"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="clientIdField">Client id</label>
            <div class="col-sm-10">
                <input id="clientIdField" name="clientId" type="number"
                       value="${clientId != null ? clientId : null}" class="form-control"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">Insert client account</button>
            </div>
        </div>
    </form>
</div>

</body>
</html>
