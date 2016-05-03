<%--
  Created by IntelliJ IDEA.
  User: stefan.pamparau
  Date: 1/20/2016
  Time: 12:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Insert book</title>
    <link rel="stylesheet"
          href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>

<jsp:include page="../../../../fragments/navigationBar.jsp"/>

<div class="container">
    <form:form action="/books/insert" modelAttribute="book" method="POST"
               cssClass="form-horizontal">
        <div class="form-group">
            <form:label path="title" cssClass="control-label col-sm-2"
                        for="titleField">Book title</form:label>
            <div class="col-sm-10">
                <form:input id="titleField" path="title" type="text" cssClass="form-control"/>
            </div>
            <div class="alert alert-info">
                <form:errors path="title"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="genre" cssClass="control-label col-sm-2" for="genreField">Book genre</form:label>
            <div class="col-sm-10">
                <form:input id="genreField" path="genre" type="text" cssClass="form-control"/>
            </div>
            <div class="alert alert-info">
                <form:errors path="genre"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="inStock" cssClass="control-label col-sm-2"
                        for="inStockField">Available in stock</form:label>
            <div class="col-sm-10">
                <form:input id="inStockField" type="number" path="inStock" cssClass="form-control"/>
            </div>
            <div class="alert alert-info">
                <form:errors path="inStock"/>
            </div>
        </div>
        <div class="form-group">
            <form:label path="price" cssClass="control-label col-sm-2"
                        for="priceField">Price</form:label>
            <div class="col-sm-10">
                <form:input id="priceField" type="number" path="price" cssClass="form-control"/>
            </div>
            <div class="alert alert-info">
                <form:errors path="price"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="authorIdField">Author ID</label>
            <div class="col-sm-10">
                <input id="authorIdField" name="authorId" type="number" value="${authorId != null ? authorId : -1}" class="form-control"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">Insert book</button>
            </div>
        </div>
    </form:form>
</div>

</body>
</html>
