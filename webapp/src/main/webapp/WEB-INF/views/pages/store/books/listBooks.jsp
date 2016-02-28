<%--
  Created by IntelliJ IDEA.
  User: stefan.pamparau
  Date: 1/20/2016
  Time: 10:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Books in the store</title>
</head>
<body>

<c:url value="/books/delete" var="booksDeleteUrl"/>

<table border="1">
    <tr>
        <td><p>Book name</p></td>
        <td><p>Number of books in store</p></td>
        <td><p>Author name</p></td>
    </tr>
    <c:forEach var="book" items="${books}">
        <tr>
            <td><c:out value="${book.name}"/></td>
            <td><c:out value="${book.inStock}"/></td>
            <td><c:out value="${book.author.name}"/></td>
            <td><a href="${booksDeleteUrl += '\\' += book.id}">Delete book</a></td>
        </tr>
    </c:forEach>
</table>

<c:if test="${message != null}">
    <h5><c:out value="${message}"/></h5>
</c:if>

</body>
</html>
