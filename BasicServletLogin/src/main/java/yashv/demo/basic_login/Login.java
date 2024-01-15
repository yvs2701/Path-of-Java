package yashv.demo.basic_login;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import yashv.demo.dao.UserDAO;

import java.io.IOException;

@WebServlet(name = "auth-login", value = "/api/auth/login")
public class Login extends HttpServlet {
    private String redirectOnSuccess, redirectOnFailure;

    public void init() {
        redirectOnSuccess = "/protected.jsp";
        redirectOnFailure = "/login.jsp";
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        handler(request, response);
    }

    private void handler(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserDAO dao = new UserDAO();
        String uname = request.getParameter("username");
        String password = request.getParameter("password");
        if (dao.check(uname, password)) {
            request.getSession().setAttribute("username", uname);
            response.sendRedirect(request.getContextPath() + redirectOnSuccess);
        } else {
            request.getSession().setAttribute("errorMessage", "Username and Password didn't match!");
            response.sendRedirect(request.getContextPath() + redirectOnFailure);
        }
    }

    public void destroy() {
    }
}