package com.proyuts.web;

import DAO.UserDao;
import com.proyuts.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
    private final UserDao userDao = new UserDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            if ("register".equals(action)) {
                User user = new User();
                user.setFullName(req.getParameter("fullName"));
                user.setEmail(req.getParameter("email"));
                user.setPassword(req.getParameter("password"));
                user.setCareer(req.getParameter("career"));
                user.setSemester(req.getParameter("semester"));
                userDao.register(user);
                resp.sendRedirect("login.jsp?msg=registered");
                return;
            }
            User user = userDao.login(req.getParameter("email"), req.getParameter("password"));
            if (user != null) {
                req.getSession(true).setAttribute("user", user);
                resp.sendRedirect("dashboard");
            } else {
                resp.sendRedirect("login.jsp?error=1");
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
