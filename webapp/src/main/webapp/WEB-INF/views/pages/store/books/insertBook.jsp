<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: stefan.pamparau
  Date: 1/20/2016
  Time: 12:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Insert book</title>
</head>
<body>

<form action="<c:url value="/books/insert"/>" method="post">
    <table border="0" align="left">
        <tr>
            <td>Book name</td>
            <td><input type="text" name="name"></td>
        </tr>
        <tr>
            <td>Available in stock</td>
            <td><input type="number" name="inStock"></td>
        </tr>
        <tr>
            <td>Author ID</td>
            <td><input type="text" name="authorID"></td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="Insert book">
            </td>
        </tr>
    </table>
</form>

</body>
</html>
