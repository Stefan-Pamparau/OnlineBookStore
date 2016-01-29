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

<table border="1">
    <tr>
        <td>Client name</td>
        <td>Client address</td>
        <td>Client email</td>
        <td>Client books</td>
        <td>Client purchases</td>
    </tr>

    <c:forEach var="client" items="${clients}">
        <tr>
            <td><c:out value="${client.name}"/></td>
            <td><c:out value="${client.address}"/></td>
            <td><c:out value="${client.email}"/></td>
            <td>
                <c:forEach var="book" items="${client.books}">
                    <c:out value="${book.name}"/>
                </c:forEach>
            </td>
            <td>
                <c:forEach var="purchase" items="${client.purchases}">
                    <c:out value="${purchase.purchaseDate}"/>
                    <c:out value="${purchase.book.name}"/>
                    <br>
                </c:forEach>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
