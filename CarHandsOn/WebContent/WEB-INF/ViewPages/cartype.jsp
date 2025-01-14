<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Car Type</title>
</head>
<body style="background-color: aqua;">
	<h1>Register for Car Service</h1>
	<form:form action="${pageContext.request.contextPath}/submitcartype" method="POST"
		modelAttribute="cartype">
		<form:radiobutton path="value" value="Maruti" label="Maruti" />
		<form:radiobutton path="value" value="Hyundai" label="Hyundai" />
		<input type="submit" value="Submit Query" />
	</form:form>
</body>
</html>