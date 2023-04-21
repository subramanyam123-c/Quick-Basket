package com.example.service.impl;

import java.util.List;

import com.example.dao.ShopDao;
import com.example.dao.Impl.ShopDaoImpl;
import com.example.model.Shop;
import com.example.service.ShopService;

public class ShopServiceImpl implements ShopService {
    
    private ShopDao shopDao;
    
    public ShopServiceImpl() {
        shopDao = new ShopDaoImpl();
    }

    @Override
    public void addShop(Shop shop) {
        shopDao.addShop(shop);
    }

    @Override
    public void updateShop(Shop shop) {
        shopDao.updateShop(shop);
    }

    @Override
    public void deleteShop(String name) {
        shopDao.deleteShop(name);
    }

    @Override
    public Shop getShopByName(String name) {
        return shopDao.getShopByName(name);
    }

    @Override
    public List<Shop> getAllShops() {
        return shopDao.getAllShops();
    }
    
}
