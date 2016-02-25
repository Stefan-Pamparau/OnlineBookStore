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

<c:url value="/authors/delete" var="deleteAuthorUrl"/>

<table border="1">
    <tr>
        <td>Author name</td>
        <td>Author age</td>
    </tr>
    <c:forEach var="author" items="${authors}">
        <tr>
            <td><c:out value="${author.name}"/></td>
            <td><c:out value="${author.age}"/></td>
            <td><a href="${deleteAuthorUrl += '\\' += author.id}">Delete author</a></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
