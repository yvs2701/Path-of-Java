package yashv.demo.basic_login;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "logout", value = "/api/auth/logout")
public class Logout extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        handler(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        handler(request, response);
    }

    private void handler(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.getSession().removeAttribute("username");
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }
}
