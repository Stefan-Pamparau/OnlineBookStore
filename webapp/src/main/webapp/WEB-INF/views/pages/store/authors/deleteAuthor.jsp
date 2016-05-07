<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
    <form method="POST" action="/authors/delete" class="form-horizontal">
        <div class="form-group">
            <label class="control-label col-sm-2" for="authorIdField">Author id</label>
            <div class="col-sm-10">
                <input id=authorIdField" type="number" name="authorId" class="form-control">
            </div>
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-default">Delete author</button>
        </div>
    </form>
</div>
</body>

</html>
