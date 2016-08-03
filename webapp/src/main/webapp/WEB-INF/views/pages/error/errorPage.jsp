<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Oops</title>
    <meta charset="UTF-8">
    <link rel="stylesheet"
          href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>

<jsp:include page="../../../fragments/navigationBar.jsp"/>

<body>
<div class="alert alert-warning">
    <strong>Warning!</strong>
    An error occurred while processing the request. Please try again.
</div>
</body>

</html>
