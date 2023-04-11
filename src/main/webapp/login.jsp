<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <jsp:include page="menu.html"/>
    <link rel="stylesheet" type="text/css" href="css/loginstyle.css">
</head>
<body>

<h3>${message}</h3>
<br>
<form class="input-form" action="AuthorizationServlet">
<table class="login-table">
	<tr>
		<td>Username:</td>
		<td><input class="login-box" name="username"/></td>
	</tr>
	<tr>
		<td>Password:</td>
		<td><input class="login-box" name="password"/></td>
	</tr>
	<c:if test="${isRegistered == false}">
		<tr>
			<td>E-mail:</td>
			<td><input class="login-box" name="email"/></td>
		</tr>
	</c:if>	
	<tr>
		<td></td>
		<td><input id="login-button" class="login-box" type="submit"/></td>
	</tr>
</table>
</form>

<jsp:include page="basement.html"/>
</body>
</html>