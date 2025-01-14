<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Car Service System</title>
</head>
<body style="background-color: aqua;">
	<center>
		<h1>Car Service System</h1>
		<p><a href="${pageContext.request.contextPath}/cartype">Select Car Type</a></p>
		<p><a href="${pageContext.request.contextPath}/displayreport">Display Report</a></p>
	</center>
</body>
</html>