package com.example.dao.Impl;

import com.example.dao.UserDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.model.User;
import com.example.util.DBUtil;

public class UserDAOImpl implements UserDAO {
    
    private Connection connection;
    
    public UserDAOImpl() throws SQLException {
        connection = DBUtil.getConnection();
    }

    @Override
    public User getUser(String email) {
        User user = null;
        try {
            String query = "SELECT * FROM users WHERE email = ?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new User(rs.getString("email"), rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void addUser(User user) {
        try {
            String query = "INSERT INTO users (email, password) VALUES (?, ?)";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
