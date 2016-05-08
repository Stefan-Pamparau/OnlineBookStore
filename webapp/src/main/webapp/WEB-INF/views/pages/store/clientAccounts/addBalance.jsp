<%--
  Created by IntelliJ IDEA.
  User: stefan.pamparau
  Date: 5/4/2016
  Time: 10:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Add balance</title>
    <link rel="stylesheet"
          href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>

<body>
<jsp:include page="../../../../fragments/navigationBar.jsp"/>

<div class="container">
    <form action="/clientAccounts/addBalance" method="post" class="form-horizontal">
        <div class="form-group">
            <label class="control-label col-sm-2" for="clientAccountIdField">Client Id</label>
            <div class="col-sm-10">
                <input id="clientAccountIdField" name="clientAccountId" type="number"
                       value="${clientAccountId != null ? clientAccountId : null}"
                       class="form-control"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="balanceField">Balance:</label>
            <div class="col-sm-10">
                <input id="balanceField" name="balance" type="number" class="form-control"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">Add balance</button>
            </div>
        </div>
    </form>
</div>

</body>
</html>
