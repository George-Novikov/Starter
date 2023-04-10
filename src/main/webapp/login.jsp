<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <jsp:include page="menu.html"/>
    <link href="/css/loginstyle.css" rel="stylesheet" type="text/css" />
</head>
<body>

<h3>${message}</h3>
<br>
<form action="AuthorizationServlet">
    Username: <input class ="login-box" name="username"/>
    <br><br>
    Password: <input class ="login-box" name="password"/>
    <br><br>
    E-mail: <input class ="login-box" name="email"/>
    <br><br>
    <input type="submit"/>
</form>

<jsp:include page="basement.html"/>
</body>
</html>