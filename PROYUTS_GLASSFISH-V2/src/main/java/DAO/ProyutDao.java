package DAO;

import com.proyuts.model.Proyut;
import com.proyuts.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProyutDao {
    public void create(Proyut p) throws SQLException {
        String sql = "INSERT INTO proyuts(user_id,title,description,category,score,completed_date) VALUES(?,?,?,?,?,?)";
        try (Connection c = DatabaseConnection.getConnection(); PreparedStatement s = c.prepareStatement(sql)) {
            s.setInt(1, p.getUserId());
            s.setString(2, p.getTitle());
            s.setString(3, p.getDescription());
            s.setString(4, p.getCategory());
            s.setInt(5, p.getScore());
            s.setDate(6, Date.valueOf(p.getCompletedDate()));
            s.executeUpdate();
        }
    }

    public void update(Proyut p) throws SQLException {
        String sql = "UPDATE proyuts SET title=?,description=?,category=?,score=?,completed_date=? WHERE id=? AND user_id=?";
        try (Connection c = DatabaseConnection.getConnection(); PreparedStatement s = c.prepareStatement(sql)) {
            s.setString(1, p.getTitle());
            s.setString(2, p.getDescription());
            s.setString(3, p.getCategory());
            s.setInt(4, p.getScore());
            s.setDate(5, Date.valueOf(p.getCompletedDate()));
            s.setInt(6, p.getId());
            s.setInt(7, p.getUserId());
            s.executeUpdate();
        }
    }

    public void delete(int id, int userId) throws SQLException {
        try (Connection c = DatabaseConnection.getConnection(); PreparedStatement s = c.prepareStatement("DELETE FROM proyuts WHERE id=? AND user_id=?")) {
            s.setInt(1, id);
            s.setInt(2, userId);
            s.executeUpdate();
        }
    }

    public List<Proyut> listByUser(int userId) throws SQLException {
        List<Proyut> list = new ArrayList<>();
        String sql = "SELECT * FROM proyuts WHERE user_id=? ORDER BY completed_date DESC";
        try (Connection c = DatabaseConnection.getConnection(); PreparedStatement s = c.prepareStatement(sql)) {
            s.setInt(1, userId);
            try (ResultSet rs = s.executeQuery()) {
                while (rs.next()) {
                    Proyut p = new Proyut();
                    p.setId(rs.getInt("id"));
                    p.setUserId(rs.getInt("user_id"));
                    p.setTitle(rs.getString("title"));
                    p.setDescription(rs.getString("description"));
                    p.setCategory(rs.getString("category"));
                    p.setScore(rs.getInt("score"));
                    p.setCompletedDate(rs.getDate("completed_date").toLocalDate());
                    list.add(p);
                }
            }
        }
        return list;
    }


    public List<Proyut> topByScore(int userId, int limit) throws SQLException {
        List<Proyut> list = new ArrayList<>();
        String sql = "SELECT * FROM proyuts WHERE user_id=? ORDER BY score DESC, completed_date DESC LIMIT ?";
        try (Connection c = DatabaseConnection.getConnection(); PreparedStatement s = c.prepareStatement(sql)) {
            s.setInt(1, userId);
            s.setInt(2, limit);
            try (ResultSet rs = s.executeQuery()) {
                while (rs.next()) {
                    Proyut p = new Proyut();
                    p.setId(rs.getInt("id"));
                    p.setUserId(rs.getInt("user_id"));
                    p.setTitle(rs.getString("title"));
                    p.setDescription(rs.getString("description"));
                    p.setCategory(rs.getString("category"));
                    p.setScore(rs.getInt("score"));
                    p.setCompletedDate(rs.getDate("completed_date").toLocalDate());
                    list.add(p);
                }
            }
        }
        return list;
    }

    public int totalCount(int userId) throws SQLException {
        return countBySql("SELECT COUNT(*) FROM proyuts WHERE user_id=?", userId);
    }

    public double averageByPeriod(String period, int userId) throws SQLException {
        String sql = switch (period) {
            case "week" -> "SELECT COUNT(*) / GREATEST(TIMESTAMPDIFF(WEEK, MIN(completed_date), NOW()) + 1,1) AS avg_val FROM proyuts WHERE user_id=?";
            case "month" -> "SELECT COUNT(*) / GREATEST(TIMESTAMPDIFF(MONTH, MIN(completed_date), NOW()) + 1,1) AS avg_val FROM proyuts WHERE user_id=?";
            case "year" -> "SELECT COUNT(*) / GREATEST(TIMESTAMPDIFF(YEAR, MIN(completed_date), NOW()) + 1,1) AS avg_val FROM proyuts WHERE user_id=?";
            default -> "SELECT COUNT(*) / GREATEST((TIMESTAMPDIFF(MONTH, MIN(completed_date), NOW()) + 1)/6,1) AS avg_val FROM proyuts WHERE user_id=?";
        };
        try (Connection c = DatabaseConnection.getConnection(); PreparedStatement s = c.prepareStatement(sql)) {
            s.setInt(1, userId);
            try (ResultSet rs = s.executeQuery()) {
                return rs.next() ? rs.getDouble("avg_val") : 0;
            }
        }
    }

    private int countBySql(String sql, int userId) throws SQLException {
        try (Connection c = DatabaseConnection.getConnection(); PreparedStatement s = c.prepareStatement(sql)) {
            s.setInt(1, userId);
            try (ResultSet rs = s.executeQuery()) {
                return rs.next() ? rs.getInt(1) : 0;
            }
        }
    }
}
