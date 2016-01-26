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
    <title>Purchase book</title>
</head>
<body>

<form:form action="/clients/purchase" method="post">
    <table border="0" align="left">
        <tr>
            <td><label>Client ID</label></td>
            <td><input name="clientID" type="number"></td>
        </tr>
        <tr>
            <td><label>Book ID</label></td>
            <td><input name="bookID" type="number"></td>
        </tr>
        <tr>
            <td><input type="submit" value="Purchase book"></td>
        </tr>
    </table>
</form:form>

</body>
</html>
