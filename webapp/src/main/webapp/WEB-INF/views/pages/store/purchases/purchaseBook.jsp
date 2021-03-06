<%--
  Created by IntelliJ IDEA.
  User: stefan.pamparau
  Date: 1/25/2016
  Time: 11:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Purchase book</title>
    <link rel="stylesheet"
          href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>

<jsp:include page="../../../../fragments/navigationBar.jsp"/>

<div class="container">
    <form:form action="/purchases/purchase" method="post" cssClass="form-horizontal">
        <div class=form-group>
            <label class="control-label col-sm-2" for="clientIdField">Client Id</label>
            <div class="col-sm-10">
                <input id="clientIdField" name="clientAccountId" type="number"
                       value="${clientAccountId != null ? clientAccountId : null}"
                       class="form-control">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="bookIdField">Book Id</label>
            <div class="col-sm-10">
                <input id="bookIdField" name="bookId" type="number" class="form-control">
            </div>
        </div>
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">Purchase book</button>
        </div>
    </form:form>
</div>

</body>
</html>
