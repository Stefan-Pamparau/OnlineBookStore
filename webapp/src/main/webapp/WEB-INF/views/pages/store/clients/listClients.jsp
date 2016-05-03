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
            <th>Client name</th>
            <th>Client address</th>
            <th>Client serial id</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="client" items="${clients}" varStatus="loopStatus">
        <tr>
            <td><c:out value="${loopStatus.index}"/></td>
            <td><c:out value="${client.name}"/></td>
            <td><c:out value="${client.address}"/></td>
            <td><c:out value="${client.serialId}"/></td>
            <td><a href="${clientListClientAccountsUrl}">Show client accounts</a></td>
            <td><a href="${clientAddAccountUrl += "\\" += client.id}">Add client account</a></td>
            <td><a href="${clientsDeleteUrl += "\\" += client.id}">Delete client</a></td>
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
