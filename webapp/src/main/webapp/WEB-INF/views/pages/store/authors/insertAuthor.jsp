<%--
  Created by IntelliJ IDEA.
  User: stefan.pamparau
  Date: 1/20/2016
  Time: 11:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Insert author</title>
    <link rel="stylesheet"
          href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
<h3 align="left">Please insert the author's information</h3>

<jsp:include page="../../../../fragments/navigationBar.jsp"/>

<div class="container">
    <form method="POST" action="/authors/insert" class="form-horizontal">
        <div class=form-group>
            <label for="nameField" class="control-label col-sm-2">Name:</label>
            <div class="col-sm-10">
                <input id="nameField" type="text" name="name" class="form-control"/>
            </div>
        </div>
        <div class=form-group>
            <label for="ageField" class="control-label col-sm-2">Age:</label>
            <div class="col-sm-10">
                <input id="ageField" type="text" name="age" class="form-control"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">Insert author</button>
            </div>
        </div>
    </form>
</div>

</body>
</html>
