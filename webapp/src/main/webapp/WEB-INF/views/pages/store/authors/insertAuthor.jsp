<%--
  Created by IntelliJ IDEA.
  User: stefan.pamparau
  Date: 1/20/2016
  Time: 11:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
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
    <form:form method="POST" modelAttribute="author" action="/authors/insert"
               cssClass="form-horizontal">
        <div class=form-group>
            <form:label path="name" for="nameField"
                        cssClass="control-label col-sm-2">Name:</form:label>
            <div class="col-sm-10">
                <form:input id="nameField" type="text" path="name" cssClass="form-control"/>
            </div>
            <div class="alert alert-info">
                <form:errors path="name"/>
            </div>
        </div>
        <div class=form-group>
            <form:label path="name" for="ageField"
                        cssClass="control-label col-sm-2">Age:</form:label>
            <div class="col-sm-10">
                <form:input id="ageField" type="text" path="age" cssClass="form-control"/>
            </div>
            <div class="alert alert-info">
                <form:errors path="age"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">Insert author</button>
            </div>
        </div>
    </form:form>
</div>

</body>
</html>
