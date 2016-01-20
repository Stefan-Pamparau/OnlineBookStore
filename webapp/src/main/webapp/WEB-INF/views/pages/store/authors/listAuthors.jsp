<%--
  Created by IntelliJ IDEA.
  User: stefan.pamparau
  Date: 1/19/2016
  Time: 4:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Authors</title>
</head>
<body>

<c:forEach var="author" items="${authors}">
    <p><c:out value="${author}"/></p>
</c:forEach>

</body>
</html>
