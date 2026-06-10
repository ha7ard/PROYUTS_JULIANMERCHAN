package DAO;

import com.proyuts.model.Course;
import com.proyuts.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDao {
    public List<Course> listAll() throws SQLException {
        List<Course> list = new ArrayList<>();
        try (Connection c = DatabaseConnection.getConnection(); PreparedStatement s = c.prepareStatement("SELECT * FROM courses ORDER BY name")) {
            try (ResultSet rs = s.executeQuery()) {
                while (rs.next()) {
                    Course course = new Course();
                    course.setId(rs.getInt("id"));
                    course.setName(rs.getString("name"));
                    course.setArea(rs.getString("area"));
                    course.setProyutsReward(rs.getInt("proyuts_reward"));
                    course.setSchedule(rs.getString("schedule"));
                    list.add(course);
                }
            }
        }
        return list;
    }

    public void create(Course cObj) throws SQLException {
        String sql = "INSERT INTO courses(name,area,proyuts_reward,schedule) VALUES(?,?,?,?)";
        try (Connection c = DatabaseConnection.getConnection(); PreparedStatement s = c.prepareStatement(sql)) {
            s.setString(1, cObj.getName());
            s.setString(2, cObj.getArea());
            s.setInt(3, cObj.getProyutsReward());
            s.setString(4, cObj.getSchedule());
            s.executeUpdate();
        }
    }

    public void update(Course cObj) throws SQLException {
        String sql = "UPDATE courses SET name=?, area=?, proyuts_reward=?, schedule=? WHERE id=?";
        try (Connection c = DatabaseConnection.getConnection(); PreparedStatement s = c.prepareStatement(sql)) {
            s.setString(1, cObj.getName());
            s.setString(2, cObj.getArea());
            s.setInt(3, cObj.getProyutsReward());
            s.setString(4, cObj.getSchedule());
            s.setInt(5, cObj.getId());
            s.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        try (Connection c = DatabaseConnection.getConnection(); PreparedStatement s = c.prepareStatement("DELETE FROM courses WHERE id=?")) {
            s.setInt(1, id);
            s.executeUpdate();
        }
    }

    public void enroll(int userId, int courseId) throws SQLException {
        try (Connection c = DatabaseConnection.getConnection(); PreparedStatement s = c.prepareStatement("INSERT IGNORE INTO user_courses(user_id,course_id) VALUES(?,?)")) {
            s.setInt(1, userId);
            s.setInt(2, courseId);
            s.executeUpdate();
        }
    }

    public void unenroll(int userId, int courseId) throws SQLException {
        try (Connection c = DatabaseConnection.getConnection(); PreparedStatement s = c.prepareStatement("DELETE FROM user_courses WHERE user_id=? AND course_id=?")) {
            s.setInt(1, userId);
            s.setInt(2, courseId);
            s.executeUpdate();
        }
    }

    public List<Integer> enrolledCourseIds(int userId) throws SQLException {
        List<Integer> ids = new ArrayList<>();
        try (Connection c = DatabaseConnection.getConnection(); PreparedStatement s = c.prepareStatement("SELECT course_id FROM user_courses WHERE user_id=?")) {
            s.setInt(1, userId);
            try (ResultSet rs = s.executeQuery()) {
                while (rs.next()) {
                    ids.add(rs.getInt(1));
                }
            }
        }
        return ids;
    }
}
