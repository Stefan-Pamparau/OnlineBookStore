<%--
  Created by IntelliJ IDEA.
  User: stefan.pamparau
  Date: 1/14/2016
  Time: 11:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Bad login credentials</title>
</head>
<body>
    <p>Invalid login credentials provided!</p>
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <p>Sorry. User with credentials: <b>${pageContext.request.userPrincipal.name}</b> does not have access to the requested page.</p>
    </c:if>

    <p>Please follow the following link to login with proper credentials: <a href="<c:url value="/login"/>">Login</a></p>

</body>
</html>
