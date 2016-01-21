<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: stefan.pamparau
  Date: 1/20/2016
  Time: 11:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete author</title>
</head>
<body>

<form method="POST" action="<c:url value="/authors/delete"/>">
    <table border="0" align="left">
        <tr>
            <td>Author ID</td>
            <td><label><input type="number" name="authorID"/></label></td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="Delete author">
            </td>
        </tr>
    </table>
</form>

</body>
</html>
