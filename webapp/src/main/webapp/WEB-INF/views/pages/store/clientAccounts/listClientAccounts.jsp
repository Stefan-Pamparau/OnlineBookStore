<%--
  Created by IntelliJ IDEA.
  User: stefan.pamparau
  Date: 5/3/2016
  Time: 3:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>List client accounts list</title>
    <link rel="stylesheet"
          href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>

<jsp:include page="../../../../fragments/navigationBar.jsp"/>

<c:url value="/purchases/list" var="viewPurchasesUrl"/>
<c:url value="/clientAccounts/addBalance" var="addBalanceUrl"/>
<c:url value="/purchases/purchase" var="purchaseBookUrl"/>
<c:url value="/clientAccounts/delete" var="deleteClientAccountUrl"/>

<div class="table-responsive">
    <table class="table">
        <thead>
        <tr>
            <th>#</th>
            <th>Email</th>
            <th>Password</th>
            <th>Creation date</th>
            <th>Balance</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="clientAccount" items="${clientAccounts}" varStatus="loopStatus">
        <tr>
            <td><c:out value="${loopStatus.index}"/></td>
            <td><c:out value="${clientAccount.email}"/></td>
            <td><c:out value="${clientAccount.password}"/></td>
            <td><c:out value="${clientAccount.creationDate}"/></td>
            <td><c:out value="${clientAccount.balance}"/></td>
            <td><a href="${viewPurchasesUrl += '/' += clientAccount.id}">View purchases</a></td>
            <td><a href="${addBalanceUrl += '/' += clientAccount.id}">Add balance</a></td>
            <td><a href="${purchaseBookUrl += '/' += clientAccount.id}">Purchase book</a></td>
            <td><a href="${deleteClientAccountUrl += '/' += clientAccount.id}">Delete</a></td>
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
