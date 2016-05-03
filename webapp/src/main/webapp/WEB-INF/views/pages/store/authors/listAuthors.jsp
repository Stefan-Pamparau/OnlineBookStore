<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Authors</title>
    <link rel="stylesheet"
          href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>

<jsp:include page="../../../../fragments/navigationBar.jsp"/>

<c:url value="/books/insert" var="bookInsertUrl"/>
<c:url value="/authors/delete" var="deleteAuthorUrl"/>

<div class="table-responsive">
    <table class="table">
        <thead>
        <tr>
            <th>#</th>
            <th>Author name</th>
            <th>Author age</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="author" items="${authors}" varStatus="loopStatus">
            <tr>
                <td><c:out value="${loopStatus.index}"/></td>
                <td><c:out value="${author.name}"/></td>
                <td><c:out value="${author.age}"/></td>
                <td><a href="${bookInsertUrl += '\\' += author.id}">Insert book for author</a></td>
                <td><a href="${deleteAuthorUrl += '\\' += author.id}">Delete author</a></td>
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
