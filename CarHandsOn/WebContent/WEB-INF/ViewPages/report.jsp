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
<title>Car Report</title>
</head>
<body style="background-color: aqua;">
	<center>
		<h1>Car Service Report</h1>
		<%-- TODO ADD TABLE DETAILS --%>
		<!-- NEW CHANGES -->
		<table border="1">
			<tr>
				<th>Car Type</th>
				<th>Car number</th>
				<th>Username</th>
				<th>Total Repair Cost</th>
			</tr>
			<c:forEach items="${carBeans}" var="bean">
				<tr>
					<td><c:out value="${bean.getCarType()}" /></td>
					<td><c:out value="${bean.getCarNumber()}" /></td>
					<td><c:out value="${bean.getUsername()}" /></td>
					<td><c:out value="${bean.getTotalRepairCost()}" /></td>
				</tr>
			</c:forEach>
		</table>
		<!-- END NEW CHANGES -->
		<p><a href="${pageContext.request.contextPath}/">HOME</a></p>
	</center>
</body>
</html>