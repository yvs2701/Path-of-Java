<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Form Page</title>
</head>
<body style="background-color: aqua;">
	<h1>Register for Car Service</h1>
	<%-- <form:form action="${pageContext.request.contextPath}/submitcartype"></form:form> --%>
	<%-- FIXED URL --%>
	<form:form action="${pageContext.request.contextPath}/submitform" method="POST" modelAttribute="carbean">
		<%-- NEW EDITS --%>
		<form:hidden path="carType" value="${cartype}" />
		<%-- END NEW EDITS --%>
		<%-- NEW EDITS --%>
		<c:if test="${not empty carTypeError}">
			<p style="color: red;">
				${carTypeError}
			</p>
			<br>
		</c:if>
		<%-- END NEW EDITS --%>
		Car Number: <form:input path="carNumber" />
		<br>
		<%-- NEW EDITS --%>
		<c:if test="${not empty carNumberError}">
			<p style="color: red;">
				${carNumberError}
			</p>
			<br>
		</c:if>
		<%-- END NEW EDITS --%>
		Part name: <form:select path="partId">
			<form:options items="${partDetails}" />
		</form:select>
		<br>
		<%-- NEW EDITS --%>
		<c:if test="${not empty partIdError}">
			<p style="color: red;">
				${partIdError}
			</p>
			<br>
		</c:if>
		<%-- END NEW EDITS --%>
		Username: <form:input path="username" />
		<br>
		<%-- NEW EDITS --%>
		<c:if test="${not empty usernameError}">
			<p style="color: red;">
				${usernameError}
			</p>
			<br>
		</c:if>
		<%-- END NEW EDITS --%>
		<input type="submit" value="Book Service" />
	</form:form>
	<a href="${pageContext.request.contextPath}">HOME</a>
</body>
</html>