<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    String message = "";
%>
<!DOCTYPE html>
<html>
<head>
    <title>Starter</title>
    <jsp:include page="menu.html"/>
</head>
<body>
<h3><%= "Welcome! This is a working instance of a Tomcat application server." %></h3>
<br/>
<p>There is a good chance that Stock Watcher service is online:</p>
<p><a href="http://localhost:8081/StockWatcher/">StockWatcher</a></p>
<!-- <a href="hello-servlet">Hello Servlet</a> -->
<jsp:include page="basement.html"/>
</body>
</html>