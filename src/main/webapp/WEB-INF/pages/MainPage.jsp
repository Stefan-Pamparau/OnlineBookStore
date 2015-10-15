<%--
  Created by IntelliJ IDEA.
  User: stefan.pamparau
  Date: 10/15/2015
  Time: 10:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Online book store</title>
</head>
<body>

<table width="100%" border="1">
  <c:forEach var="book" items="${bookList}">
      <tr>
          <td>"${book.bookName}"</td>
          <td>"${book.bookAuthor}"</td>
      </tr>
  </c:forEach>
</table>

</body>
</html>
