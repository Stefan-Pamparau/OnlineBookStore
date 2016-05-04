<%--
  Created by IntelliJ IDEA.
  User: stefan.pamparau
  Date: 1/25/2016
  Time: 11:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>

<head>
    <title>Insert client</title>
    <link rel="stylesheet"
          href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>

<body>

<jsp:include page="../../../../fragments/navigationBar.jsp"/>

<div class="container">
    <form:form action="/clients/insert" method="POST" modelAttribute="client"
               cssClass="form-horizontal">
        <div class="form-group">
            <form:label path="name" cssClass="control-label col-sm-2"
                        form="nameField">Name:</form:label>
            <div class="col-sm-10">
                <form:input id="nameField" path="name" type="text" cssClass="form-control"/>
            </div>
            <div class="alert alert-info">
                <form:errors path="name"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="address" cssClass="control-label col-sm-2"
                        form="addressField">Address:</form:label>
            <div class="col-sm-10">
                <form:input id="addressField" path="address" type="text" cssClass="form-control"/>
            </div>
            <div class="alert alert-info">
                <form:errors path="address"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="serialId" cssClass="control-label col-sm-2"
                        form="serialIdField">Serial id:</form:label>
            <div class="col-sm-10">
                <form:input id="serialIdField" path="serialId" type="text" cssClass="form-control"/>
            </div>
            <div class="alert alert-info">
                <form:errors path="serialId"/>
            </div>
        </div>
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">Insert client</button>
        </div>
    </form:form>
</div>

</body>
</html>
