<%--
  Created by IntelliJ IDEA.
  User: stefan.pamparau
  Date: 1/25/2016
  Time: 11:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>All clients of the store</title>
</head>
<body>

<c:forEach var="client" items="${clients}">
    <p><c:out value="${client}"/></p>
</c:forEach>

</body>
</html>
