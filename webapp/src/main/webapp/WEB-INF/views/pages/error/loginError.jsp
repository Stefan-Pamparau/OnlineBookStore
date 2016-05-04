<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <title>Bad login credentials</title>
    <link rel="stylesheet" href="<c:url value="/static/css/loginError.css"/> ">
    <link rel="stylesheet"
          href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>

<body>
<div class="alert alert-warning">
    <p>Invalid login credentials provided!</p>
</div>

<div class="alert alert-info">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <p>Sorry. User with credentials: <b>${pageContext.request.userPrincipal.name}</b> does not
            have
            access to the requested page.</p>
    </c:if>

    <p>Please follow the following link to login with proper credentials: <a
            href="<c:url value="/login"/>">Login</a></p>
</div>
</body>
</html>
