package com.example.dao.Impl;

import com.example.dao.ZipCodeDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.model.ZipCode;
import com.example.util.DBUtil;

public class ZipCodeDaoImpl implements ZipCodeDao {
    
    private Connection connection;
    
    public ZipCodeDaoImpl() {
        connection = DBUtil.getConnection();
    }
    
    @Override
    public void addZipCode(ZipCode zipCode) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO zipcodes(code) VALUES (?)");
            preparedStatement.setString(1, zipCode.getCode());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateZipCode(ZipCode zipCode) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE zipcodes SET code=? WHERE code=?");
            preparedStatement.setString(1, zipCode.getCode());
            preparedStatement.setString(2, zipCode.getCode());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteZipCode(String code) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM zipcodes WHERE code=?");
            preparedStatement.setString(1, code);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ZipCode getZipCodeByCode(String code) {
        ZipCode zipCode = new ZipCode();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM zipcodes WHERE code=?");
            preparedStatement.setString(1, code);
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs.next()) {
                zipCode.setCode(rs.getString("code"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return zipCode;
    }

    @Override
    public List<ZipCode> getAllZipCodes() {
        List<ZipCode> zipCodes = new ArrayList<ZipCode>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM zipcodes");
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                ZipCode zipCode = new ZipCode();
                zipCode.setCode(rs.getString("code"));
                zipCodes.add(zipCode);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return zipCodes;
    }
}
