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
    <link rel="stylesheet"
          href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>

<jsp:include page="../../../../fragments/navigationBar.jsp"/>

<c:url value="/books/delete" var="booksDeleteUrl"/>

<div class="table-responsive">
    <table class="table">
        <thead>
        <tr>
            <th>#</th>
            <th>Book title</th>
            <th>Book genre</th>
            <th>Number of books in store</th>
            <th>Price</th>
            <th>Author name</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="book" items="${books}" varStatus="loopStatus">
            <tr>
                <td><c:out value="${loopStatus.index}"/></td>
                <td><c:out value="${book.title}"/></td>
                <td><c:out value="${book.genre}"/></td>
                <td><c:out value="${book.inStock}"/></td>
                <td><c:out value="${book.price}"/></td>
                <td><c:out value="${book.author.name}"/></td>
                <td><a href="${booksDeleteUrl += '\\' += book.id}">Delete book</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<c:if test="${message != null}">
    <div class="alert alert-info">
        <h5><c:out value="${message}"/></h5>
    </div>
</c:if>

</body>
</html>
