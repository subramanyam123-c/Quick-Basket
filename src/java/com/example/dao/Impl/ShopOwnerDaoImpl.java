package com.example.dao.Impl;

import com.example.dao.ShopOwnerDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.Shop;
import com.example.model.ShopOwner;
import com.example.model.ZipCode;
import com.example.util.DBUtil;

public class ShopOwnerDaoImpl implements ShopOwnerDao {
    
    private Connection connection;
    
    public ShopOwnerDaoImpl() {
        connection = DBUtil.getConnection();
    }
    
    @Override
    public void addShopOwner(ShopOwner shopOwner) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO shop_owners(name) VALUES (?)");
            preparedStatement.setString(1, shopOwner.getName());
            preparedStatement.executeUpdate();
            
            List<Shop> shops = shopOwner.getShops();
            for (Shop shop : shops) {
                PreparedStatement shopStatement = connection.prepareStatement("INSERT INTO shops(name, zipcode, status, owner_name) VALUES (?, ?, ?, ?)");
                shopStatement.setString(1, shop.getName());
                shopStatement.setString(2, shop.getZipCode().getCode());
                shopStatement.setBoolean(3, shop.isStatus());
                shopStatement.setString(4, shopOwner.getName());
                shopStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateShopOwner(ShopOwner shopOwner) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE shop_owners SET name=? WHERE name=?");
            preparedStatement.setString(1, shopOwner.getName());
            preparedStatement.setString(2, shopOwner.getName());
            preparedStatement.executeUpdate();
            
            List<Shop> shops = shopOwner.getShops();
            for (Shop shop : shops) {
                PreparedStatement shopStatement = connection.prepareStatement("UPDATE shops SET zipcode=?, status=?, owner_name=? WHERE name=?");
                shopStatement.setString(1, shop.getZipCode().getCode());
                shopStatement.setBoolean(2, shop.isStatus());
                shopStatement.setString(3, shopOwner.getName());
                shopStatement.setString(4, shop.getName());
                shopStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteShopOwner(String name) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM shop_owners WHERE name=?");
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
            
            PreparedStatement shopStatement = connection.prepareStatement("DELETE FROM shops WHERE owner_name=?");
            shopStatement.setString(1, name);
            shopStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public ShopOwner getShopOwnerByName(String name){
        return null;
    }
    
    @Override
    public List<ShopOwner> getAllShopOwners(){
        return null;
    }
}
