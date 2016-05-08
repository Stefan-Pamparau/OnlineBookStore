<%--
  Created by IntelliJ IDEA.
  User: stefan.pamparau
  Date: 1/20/2016
  Time: 12:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <form action="/books/insert" method="POST" class="form-horizontal">
        <div class="form-group">
            <label class="control-label col-sm-2" for="titleField">Book title</label>
            <div class="col-sm-10">
                <input id="titleField" name="title" type="text" class="form-control"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="genreField">Book genre</label>
            <div class="col-sm-10">
                <input id="genreField" name="genre" type="text" class="form-control"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="inStockField">Available in stock</label>
            <div class="col-sm-10">
                <input id="inStockField" type="number" name="inStock" class="form-control"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2"
                   for="priceField">Price</label>
            <div class="col-sm-10">
                <input id="priceField" type="number" name="price" class="form-control"/>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-2" for="authorIdField">Author ID</label>
            <div class="col-sm-10">
                <input id="authorIdField" name="authorId" type="number" value="${authorId != null ? authorId : null}"
                       class="form-control"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">Insert book</button>
            </div>
        </div>
    </form>
</div>

</body>
</html>
