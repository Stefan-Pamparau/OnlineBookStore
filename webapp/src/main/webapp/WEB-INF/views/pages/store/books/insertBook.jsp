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
    <title>Insert book</title>
</head>
<body>

<form:form action="/books/insert" modelAttribute="book" method="POST">
    <table border="0" align="left">
        <tr>
            <td><form:label path="name">Book name</form:label></td>
            <td><form:input path="name" type="text"/></td>
            <td><form:errors path="name"/></td>
        </tr>
        <tr>
            <td><form:label path="inStock">Available in stock</form:label></td>
            <td><form:input type="number" path="inStock"/></td>
            <td><form:errors path="inStock"/></td>
        </tr>
        <tr>
            <td><label>Author ID</label></td>
            <td><input name="authorID" type="number"/></td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="Insert book">
            </td>
        </tr>
    </table>
</form:form>

</body>
</html>
