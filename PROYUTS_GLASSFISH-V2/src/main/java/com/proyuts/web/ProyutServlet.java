package com.proyuts.web;

import DAO.ProyutDao;
import com.proyuts.model.Proyut;
import com.proyuts.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet("/proyuts")
public class ProyutServlet extends HttpServlet {
    private final ProyutDao dao = new ProyutDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        try {
            req.setAttribute("proyuts", dao.listByUser(user.getId()));
            req.setAttribute("topProyuts", dao.topByScore(user.getId(), 10));
            req.getRequestDispatcher("proyuts.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User user = (User) req.getSession().getAttribute("user");
        String action = req.getParameter("action");
        try {
            if ("delete".equals(action)) {
                dao.delete(Integer.parseInt(req.getParameter("id")), user.getId());
                resp.sendRedirect("proyuts");
                return;
            }

            Proyut p = new Proyut();
            p.setUserId(user.getId());
            p.setTitle(req.getParameter("title"));
            p.setDescription(req.getParameter("description"));
            p.setCategory(req.getParameter("category"));
            p.setScore(Integer.parseInt(req.getParameter("score")));
            p.setCompletedDate(LocalDate.parse(req.getParameter("completedDate")));

            if ("create".equals(action)) {
                dao.create(p);
            } else if ("update".equals(action)) {
                p.setId(Integer.parseInt(req.getParameter("id")));
                dao.update(p);
            }
            resp.sendRedirect("proyuts");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
