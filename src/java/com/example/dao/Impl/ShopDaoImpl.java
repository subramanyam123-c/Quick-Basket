package com.example.dao.Impl;

import com.example.dao.ShopDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Shop;
import com.example.model.ZipCode;
import com.example.util.DBUtil;

public class ShopDaoImpl implements ShopDao {
    
    private Connection connection;
    
    public ShopDaoImpl() {
        connection = DBUtil.getConnection();
    }
    
    @Override
    public void addShop(Shop shop) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO shops(name, zipcode, status) VALUES (?, ?, ?)");
            preparedStatement.setString(1, shop.getName());
            preparedStatement.setString(2, shop.getZipCode().getCode());
            preparedStatement.setBoolean(3, shop.isStatus());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateShop(Shop shop) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE shops SET zipcode=?, status=? WHERE name=?");
            preparedStatement.setString(1, shop.getZipCode().getCode());
            preparedStatement.setBoolean(2, shop.isStatus());
            preparedStatement.setString(3, shop.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteShop(String name) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM shops WHERE name=?");
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Shop getShopByName(String name) {
        Shop shop = new Shop();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM shops WHERE name=?");
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs.next()) {
                shop.setName(rs.getString("name"));
                shop.setZipCode(new ZipCode(rs.getString("zipcode")));
                shop.setStatus(rs.getBoolean("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shop;
    }

    @Override
    public List<Shop> getAllShops() {
        List<Shop> shops = new ArrayList<Shop>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM shops");
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                Shop shop = new Shop();
                shop.setName(rs.getString("name"));
                shop.setZipCode(new ZipCode(rs.getString("zipcode")));
                shop.setStatus(rs.getBoolean("status"));
                shops.add(shop);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shops;
    }
}
