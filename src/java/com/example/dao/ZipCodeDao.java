package com.example.dao;

import java.util.List;

import com.example.model.ZipCode;

public interface ZipCodeDao {
    
    public void addZipCode(ZipCode zipCode);
    public void updateZipCode(ZipCode zipCode);
    public void deleteZipCode(String code);
    public ZipCode getZipCodeByCode(String code);
    public List<ZipCode> getAllZipCodes();
    
}
