<%
    String message = pageContext.getException().getMessage();
    String type = pageContext.getException().getClass().toString();
%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>An error occured while processing your request</h2>
<p>Type <%= type%></p>
<p>Message: <%= message%></p>
</body>
</html>
