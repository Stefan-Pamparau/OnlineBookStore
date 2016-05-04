<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>Navigation bar</title>
</head>

<body>
<nav class="navbar navbar-inverse">
    <div class=container-fliud">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Online book store</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Authors
                <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="<c:url value="/authors/list"/>">Show all authors</a></li>
                    <li><a href="<c:url value="/authors/insert"/>">Insert author</a></li>
                    <li><a href="<c:url value="/authors/delete"/>">Delete author</a></li>
                </ul>
            </li>
            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Books
                <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="<c:url value="/books/list"/>">Show all books</a></li>
                    <li><a href="<c:url value="/books/insert"/>">Insert book</a></li>
                    <li><a href="<c:url value="/books/delete"/>">Delete book</a></li>
                </ul>
            </li>
            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Clients
                <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="<c:url value="/clients/list"/>">Show all clients</a></li>
                    <li><a href="<c:url value="/clients/insert"/>">Insert client</a></li>
                    <li><a href="<c:url value="/clients/delete"/>">Delete client</a></li>
                </ul>
            </li>
            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Clients
                accounts
                <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="<c:url value="/clientAccounts/list"/>">Show all client accounts</a>
                    </li>
                    <li><a href="<c:url value="/clientAccounts/insert"/>">Insert client account</a>
                    </li>
                    <li><a href="<c:url value="/clientAccounts/delete"/>">Delete client account</a>
                    </li>
                </ul>
            </li>
            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Purchases
                <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="<c:url value="/purchases/list"/>">Show all purchases</a></li>
                    <li><a href="<c:url value="/purchases/purchase"/>">Purchase book</a></li>
                </ul>
            </li>
        </ul>
    </div>
</nav>
</body>

</html>
