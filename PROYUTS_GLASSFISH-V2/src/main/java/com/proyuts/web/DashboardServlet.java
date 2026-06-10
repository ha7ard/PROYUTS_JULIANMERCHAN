package com.proyuts.web;

import DAO.ProyutDao;
import com.proyuts.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    private final ProyutDao dao = new ProyutDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        try {
            int total = dao.totalCount(user.getId());
            req.setAttribute("total", total);
            req.setAttribute("missing", Math.max(52 - total, 0));
            req.setAttribute("weekAvg", dao.averageByPeriod("week", user.getId()));
            req.setAttribute("monthAvg", dao.averageByPeriod("month", user.getId()));
            req.setAttribute("yearAvg", dao.averageByPeriod("year", user.getId()));
            req.setAttribute("semesterAvg", dao.averageByPeriod("semester", user.getId()));
            req.getRequestDispatcher("dashboard.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
