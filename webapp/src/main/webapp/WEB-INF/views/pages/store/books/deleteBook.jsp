<%--
  Created by IntelliJ IDEA.
  User: stefan.pamparau
  Date: 1/20/2016
  Time: 12:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Delete book</title>
</head>
<body>

<form:form method="POST" action="/books/delete">
    <table border="0" align="left">
        <tr>
            <td><label>Book ID</label></td>
            <td><input name="bookID" type="number"/></td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="Delete book">
            </td>
        </tr>
    </table>
</form:form>

</body>
</html>
