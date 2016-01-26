<%--
  Created by IntelliJ IDEA.
  User: stefan.pamparau
  Date: 1/25/2016
  Time: 11:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Insert client</title>
</head>
<body>

<form:form action="/clients/insert" modelAttribute="client" method="post">
    <table border="0" align="left">
        <tr>
            <td><form:label path="name">Name</form:label></td>
            <td><form:input path="name" type="text"/></td>
            <td><form:errors path="name"/></td>
        </tr>
        <tr>
            <td><form:label path="address">Address</form:label></td>
            <td><form:input path="address" type="text"/></td>
            <td><form:errors path="address"/></td>
        </tr>
        <tr>
            <td><form:label path="email">Email</form:label></td>
            <td><form:input path="email" type="text"/></td>
            <td><form:errors path="email"/></td>
        </tr>
        <tr>
            <td><form:label path="password">Password</form:label></td>
            <td><form:input path="password" type="password"/></td>
            <td><form:errors path="password"/></td>
        </tr>
        <tr>
            <td><input type="submit" name="Insert client"></td>
        </tr>
    </table>
</form:form>

</body>
</html>
