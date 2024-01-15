<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Protected Page</title>
</head>
<body>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0
    response.setHeader("Expires", "0"); // Proxies

    if (session.getAttribute("username") == null) {
        session.setAttribute("errorMessage", "Login to access protected route!");
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
%>
<h1><%= "Protected Page" %></h1>
<br>
<p>Welcome <%= session.getAttribute("username") %>!</p>
<a href="${pageContext.request.contextPath}/api/auth/logout">Logout</a>
</body>
</html>
