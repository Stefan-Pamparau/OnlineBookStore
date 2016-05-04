<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>All purchases</title>
    <link rel="stylesheet"
          href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>

<body>

<jsp:include page="../../../../fragments/navigationBar.jsp"/>

<c:url value="/clientAccounts/list" var="clientListClientAccountsUrl"/>
<c:url value="/clientAccounts/insert" var="clientAddAccountUrl"/>
<c:url value="/clients/delete" var="clientsDeleteUrl"/>

<div class="table-responsive">
    <table class="table">
        <thead>
        <tr>
            <th>#</th>
            <th>Purchase date</th>
            <th>Client account</th>
            <th>Book</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="purchase" items="${purchases}" varStatus="loopStatus">
        <tr>
            <td><c:out value="${loopStatus.index}"/></td>
            <td><c:out value="${purchase.purchaseDate}"/></td>
            <td><c:out value="${purchase.clientAccount.email}"/></td>
            <td><c:out value="${purchase.book.title}"/></td>
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
