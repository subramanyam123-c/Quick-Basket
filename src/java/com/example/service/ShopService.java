package com.example.service;

import java.util.List;

import com.example.model.Shop;

public interface ShopService {
    
    public void addShop(Shop shop);
    public void updateShop(Shop shop);
    public void deleteShop(String name);
    public Shop getShopByName(String name);
    public List<Shop> getAllShops();
    
}
