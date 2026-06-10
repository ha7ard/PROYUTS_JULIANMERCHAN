package DAO;

import com.proyuts.model.User;
import com.proyuts.util.DatabaseConnection;

import java.sql.*;

public class UserDao {
    public boolean register(User user) throws SQLException {
        String sql = "INSERT INTO users(full_name,email,password,career,semester) VALUES(?,?,?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getFullName());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getCareer());
            stmt.setString(5, user.getSemester());
            return stmt.executeUpdate() > 0;
        }
    }

    public User login(String email, String password) throws SQLException {
        String sql = "SELECT * FROM users WHERE email=? AND password=?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setFullName(rs.getString("full_name"));
                    user.setEmail(rs.getString("email"));
                    user.setCareer(rs.getString("career"));
                    user.setSemester(rs.getString("semester"));
                    return user;
                }
                return null;
            }
        }
    }

    public User findById(int id) throws SQLException {
        String sql = "SELECT * FROM users WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setFullName(rs.getString("full_name"));
                    user.setEmail(rs.getString("email"));
                    user.setCareer(rs.getString("career"));
                    user.setSemester(rs.getString("semester"));
                    return user;
                }
                return null;
            }
        }
    }
}
