<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: stefan.pamparau
  Date: 1/20/2016
  Time: 11:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Delete author</title>
    <link rel="stylesheet"
          href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>

<jsp:include page="../../../../fragments/navigationBar.jsp"/>

<div class="container">
    <form:form method="POST" action="/authors/delete" cssClass="form-horizontal">
        <div class="form-group">
            <label class="control-label col-sm-2" for="authorIdField">Author id</label>
            <div class="col-sm-10">
                <input id=authorIdField" type="number" name="authorID" class="form-control">
            </div>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-default">Delete author</button>
        </div>
    </form:form>
</div>
</body>

</html>
