<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Exception</title>
</head>
<body style="background-color: aqua;">
	<center>
		<h1>Book Management System</h1>
		<h2>Exception Handler Page</h2>
		<hr>
		<div style="color: red">
			<h3>${exception}</h3>
			<hr>
			<h3>${message}</h3>
			<hr>
		</div>
		<div>
			<a href="${pageContext.request.contextPath}/index.jsp">Home</a>
		</div>
	</center>
</body>
</html>