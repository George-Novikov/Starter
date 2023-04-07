<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <jsp:include page="menu.html"/>
</head>
<body>

<h3>${message}</h3>
<br>
<form action="AuthorizationServlet">
    Username: <input name="username"/>
    <br><br>
    Login: <input name="password"/>
    <br><br>
    <input type="submit"/>
</form>

<jsp:include page="basement.html"/>
</body>
</html>