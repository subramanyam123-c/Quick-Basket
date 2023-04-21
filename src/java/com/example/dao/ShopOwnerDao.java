package com.example.dao;

import java.util.List;

import com.example.model.ShopOwner;

public interface ShopOwnerDao {
    
    public void addShopOwner(ShopOwner shopOwner);
    public void updateShopOwner(ShopOwner shopOwner);
    public void deleteShopOwner(String name);
    public ShopOwner getShopOwnerByName(String name);
    public List<ShopOwner> getAllShopOwners();
    
}
