package com.example.dao;

import java.util.List;

import com.example.model.Shop;

public interface ShopDao {
    
    public void addShop(Shop shop);
    public void updateShop(Shop shop);
    public void deleteShop(String name);
    public Shop getShopByName(String name);
    public List<Shop> getAllShops();
    
}
