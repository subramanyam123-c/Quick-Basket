package com.example.service.impl;

import java.util.List;

import com.example.model.Shop;
import com.example.dao.ShopOwnerDao;
import com.example.dao.Impl.ShopOwnerDaoImpl;
import com.example.model.ShopOwner;
import com.example.service.ShopOwnerService;
import java.util.ArrayList;

public class ShopOwnerServiceImpl implements ShopOwnerService {
    
    private ShopOwnerDao shopOwnerDao;
    
    public ShopOwnerServiceImpl() {
        shopOwnerDao = new ShopOwnerDaoImpl();
    }

    @Override
    public void addShopOwner(ShopOwner shopOwner) {
        shopOwnerDao.addShopOwner(shopOwner);
    }

    @Override
    public void updateShopOwner(ShopOwner shopOwner) {
        shopOwnerDao.updateShopOwner(shopOwner);
    }

    @Override
    public void deleteShopOwner(String name) {
        shopOwnerDao.deleteShopOwner(name);
    }

    @Override
    public ShopOwner getShopOwnerByName(String name) {
        return shopOwnerDao.getShopOwnerByName(name);
    }

    @Override
    public List<ShopOwner> getAllShopOwners() {
        return shopOwnerDao.getAllShopOwners();
    }

    @Override
    public void addShopToOwner(ShopOwner shopOwner, Shop shop) {
        List<Shop> shops = shopOwner.getShops();
        shops.add(shop);
        shopOwner.setShops((ArrayList<Shop>) shops);
        shopOwnerDao.updateShopOwner(shopOwner);
    }

    @Override
    public void removeShopFromOwner(ShopOwner shopOwner, Shop shop) {
        List<Shop> shops = shopOwner.getShops();
        shops.remove(shop);
        shopOwner.setShops((ArrayList<Shop>) shops);
        shopOwnerDao.updateShopOwner(shopOwner);
    }
    
}
