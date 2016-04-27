<%--
  Created by IntelliJ IDEA.
  User: stefan.pamparau
  Date: 1/20/2016
  Time: 11:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
    <title>Insert author</title>
</head>
<body>
<h3 align="left">Please insert the author's information</h3>

<form:form method="POST" modelAttribute="author" action="/authors/insert">
    <table border="0" align="left">
        <tr>
            <td><form:label path="name">Name</form:label></td>
            <td><form:input path="name" type="text"/></td>
            <td><form:errors path="name"/></td>
        </tr>
        <tr>
            <td><form:label path="age">Age</form:label></td>
            <td><form:input path="age" type="number"/></td>
            <td><form:errors path="age"/>
        </tr>
        <tr>
            <td>
                <input type="submit" value="Insert author">
            </td>
        </tr>
    </table>
</form:form>

</body>
</html>
