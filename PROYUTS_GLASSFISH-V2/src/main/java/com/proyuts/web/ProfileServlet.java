package com.proyuts.web;

import DAO.UserDao;
import com.proyuts.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    private final UserDao dao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        try {
            req.setAttribute("profile", dao.findById(user.getId()));
            req.getRequestDispatcher("profile.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
