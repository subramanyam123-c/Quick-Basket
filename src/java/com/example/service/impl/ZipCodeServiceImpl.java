package com.example.service.impl;

import java.util.List;

import com.example.dao.ZipCodeDao;
import com.example.dao.Impl.ZipCodeDaoImpl;
import com.example.model.ZipCode;
import com.example.service.ZipCodeService;

public class ZipCodeServiceImpl implements ZipCodeService {
    
    private ZipCodeDao zipCodeDao;
    
    public ZipCodeServiceImpl() {
        zipCodeDao = new ZipCodeDaoImpl();
    }

    @Override
    public void addZipCode(ZipCode zipCode) {
        // Add validation logic here
        zipCodeDao.addZipCode(zipCode);
    }

    @Override
    public void updateZipCode(ZipCode zipCode) {
        // Add validation logic here
        zipCodeDao.updateZipCode(zipCode);
    }

    @Override
    public void deleteZipCode(String code) {
        // Add validation logic here
        zipCodeDao.deleteZipCode(code);
    }

    @Override
    public ZipCode getZipCodeByCode(String code) {
        return zipCodeDao.getZipCodeByCode(code);
    }

    @Override
    public List<ZipCode> getAllZipCodes() {
        return zipCodeDao.getAllZipCodes();
    }
}
