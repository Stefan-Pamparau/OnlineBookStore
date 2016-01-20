<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: stefan.pamparau
  Date: 1/20/2016
  Time: 11:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Insert author</title>
</head>
<body>
<h3 align="left">Please insert the author's information</h3>

<form method="POST" action="<c:url value="/authors/insert"/>">
    <table border="0" align="left">
        <tr>
            <td>Name</td>
            <td><input type="text" name="name"/></td>
        </tr>
        <tr>
            <td>Age</td>
            <td><input type="number" name="age"/></td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="Insert author">
            </td>
        </tr>
    </table>
</form>

</body>
</html>
