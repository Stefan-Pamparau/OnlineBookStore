<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete client account</title>
    <link rel="stylesheet"
          href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>

<jsp:include page="../../../../fragments/navigationBar.jsp"/>

<div class="container">
    <form action="/clientAccounts/delete" method="POST" class="form-horizontal">
        <div class="form-group">
            <label class="control-label col-sm-2" for="clientAccountIdField">Client Id</label>
            <div class="col-sm-10">
                <input id="clientAccountIdField" name="clientAccountId" type="number" class="form-control"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">Delete client</button>
            </div>
        </div>
    </form>
</div>

</body>
</html>
