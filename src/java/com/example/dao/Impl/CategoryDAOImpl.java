package com.example.dao.Impl;

import com.example.dao.CategoryDAO;
import com.example.model.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {
    
    private Connection conn;

    public CategoryDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void save(Category category) {
        String query = "INSERT INTO categories(name) VALUES (?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, category.getName());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error saving category: " + ex.getMessage());
        }
    }

    @Override
    public void update(Category category) {
        String query = "UPDATE categories SET name = ? WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, category.getName());
            stmt.setLong(2, category.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error updating category: " + ex.getMessage());
        }
    }

    @Override
    public void delete(Category category) {
        String query = "DELETE FROM categories WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, category.getId());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error deleting category: " + ex.getMessage());
        }
    }

    @Override
    public Category getById(Long id) {
        String query = "SELECT * FROM categories WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Category category = new Category();
                category.setId(rs.getLong("id"));
                category.setName(rs.getString("name"));
                return category;
            }
        } catch (SQLException ex) {
            System.out.println("Error getting category by ID: " + ex.getMessage());
        }
        return null;
    }

    @Override
    public List<Category> getAll() {
        String query = "SELECT * FROM categories";
        List<Category> categories = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getLong("id"));
                category.setName(rs.getString("name"));
                categories.add(category);
            }
        } catch (SQLException ex) {
            System.out.println("Error getting all categories: " + ex.getMessage());
        }
        return categories;
    }
}
