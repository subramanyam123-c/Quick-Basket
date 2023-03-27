package com.example.dao.Impl;

import com.example.dao.ProductDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Product;
import com.example.util.DBUtil;

public class ProductDaoImpl implements ProductDao {
    
    private Connection connection;
    
    public ProductDaoImpl() {
        connection = DBUtil.getConnection();
    }
    
    @Override
    public void addProduct(Product product) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO products(name, description, price, quantity, status, shop_id) VALUES (?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getQuantity());
            preparedStatement.setString(5, product.getStatus());
            preparedStatement.setInt(6, product.getShopId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProduct(Product product) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE products SET name=?, description=?, price=?, quantity=?, status=?, shop_id=? WHERE id=?");
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getQuantity());
            preparedStatement.setString(5, product.getStatus());
            preparedStatement.setInt(6, product.getShopId());
            preparedStatement.setInt(7, product.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProduct(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM products WHERE id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Product getProductById(int id) {
        Product product = new Product();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM products WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs.next()) {
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setStatus(rs.getString("status"));
                product.setShopId(rs.getInt("shop_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<Product>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM products");
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setPrice(rs.getDouble("price"));
                product.setQuantity(rs.getInt("quantity"));
                product.setStatus(rs.getString("status"));
                product.setShopId(rs.getInt("shop_id"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
