<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
<%
    System.out.println(request.getContextPath());
    String errorMessage = null;
    if (session.getAttribute("errorMessage") != null) {
        errorMessage = (String) session.getAttribute("errorMessage");
        session.removeAttribute("errorMessage");
    }
%>
    <form action="${pageContext.request.contextPath}/api/auth/login" method="post">
        <% if (errorMessage != null) %>
        <div style="color: red"><%= errorMessage %></div>
        <label for="usrname">
            Name:
            <input id="usrname" name="username" type="text" placeholder="admin" />
        </label>
        <br>
        <label for="psswd">
            Password:
            <input id="psswd" name="password" type="password" placeholder="admin" />
        </label>
        <button type="submit">Submit</button>
    </form>
</body>
</html>
