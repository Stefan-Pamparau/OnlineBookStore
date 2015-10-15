<%--
  Created by IntelliJ IDEA.
  User: stefan.pamparau
  Date: 10/15/2015
  Time: 9:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>

<body>

<form method="post" action="/login">
    <table border="0" align="center">
        <tr>
            <td>Username</td>
            <td><input type="text" name="username"/></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="password" name="password" /></td>
        </tr>
    </table>
    <div align="center"><input type="submit" value="Login"></div>
</form>

</body>

</html>
