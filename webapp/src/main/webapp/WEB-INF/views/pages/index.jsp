<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <title>Online book store</title>
    <meta charset="UTF-8">
</head>

<body>
<h1 align="center">Welcome to my online book store</h1>
<h2 align="center">You have the following possibilities</h2>
<h3 align="left">For listing all authors go to:<a href="<c:url value="/authors/list"/>">List all authors</a></h3>
<h3 align="left">For inserting an author go to:<a href="<c:url value="/authors/insert"/>">Insert author</a></h3>
<h3 align="left">For deleteing an author go to:<a href="<c:url value="/authors/delete"/>">Delete author</a></h3>
<h3 align="left">For listing all available books go to: <a href="<c:url value="/books/list"/>">List all books</a></h3>
<h3 align="left">For inserting a book go to: <a href="<c:url value="/books/insert"/>">Insert book</a></h3>
<h3 align="left">For deleting a book go to: <a href="<c:url value="/books/delete"/>">Delete book</a></h3>
<h3 align="left">For listing all the clients go to: <a href="<c:url value="/clients/list"/>">List clients</a></h3>
<h3 align="left">For inserting a client go to: <a href="<c:url value="/clients/insert"/>">Insert client</a></h3>
<h3 align="left">For deleting a client go to: <a href="<c:url value="/clients/delete"/>">Delete client</a></h3>
<h3 align="left">For making a purchase go to: <a href="<c:url value="/clients/purchase"/>">Purchase book</a></h3>
</body>

</html>
