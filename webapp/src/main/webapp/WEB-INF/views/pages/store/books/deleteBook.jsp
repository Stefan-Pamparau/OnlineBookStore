<%--
  Created by IntelliJ IDEA.
  User: stefan.pamparau
  Date: 1/20/2016
  Time: 12:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Delete book</title>
</head>
<body>

<form method="POST" action="<c:url value="/books/delete"/>">
    <table border="0" align="left">
        <tr>
            <td>Book ID</td>
            <td><label><input type="number" name="bookID"/></label></td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="Delete book">
            </td>
        </tr>
    </table>
</form>

</body>
</html>
