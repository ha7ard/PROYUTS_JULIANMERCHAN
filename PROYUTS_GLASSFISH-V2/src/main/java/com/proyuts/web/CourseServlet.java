package com.proyuts.web;

import DAO.CourseDao;
import com.proyuts.model.Course;
import com.proyuts.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/courses")
public class CourseServlet extends HttpServlet {
    private final CourseDao dao = new CourseDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        try {
            req.setAttribute("courses", dao.listAll());
            req.setAttribute("enrolledIds", dao.enrolledCourseIds(user.getId()));
            req.getRequestDispatcher("courses.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User user = (User) req.getSession().getAttribute("user");
        String action = req.getParameter("action");
        try {
            if ("enroll".equals(action)) {
                dao.enroll(user.getId(), Integer.parseInt(req.getParameter("courseId")));
            } else if ("unenroll".equals(action)) {
                dao.unenroll(user.getId(), Integer.parseInt(req.getParameter("courseId")));
            } else if ("delete".equals(action)) {
                dao.delete(Integer.parseInt(req.getParameter("id")));
            } else {
                Course cObj = new Course();
                cObj.setName(req.getParameter("name"));
                cObj.setArea(req.getParameter("area"));
                cObj.setProyutsReward(Integer.parseInt(req.getParameter("proyutsReward")));
                cObj.setSchedule(req.getParameter("schedule"));
                if ("create".equals(action)) {
                    dao.create(cObj);
                } else if ("update".equals(action)) {
                    cObj.setId(Integer.parseInt(req.getParameter("id")));
                    dao.update(cObj);
                }
            }
            resp.sendRedirect("courses");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
