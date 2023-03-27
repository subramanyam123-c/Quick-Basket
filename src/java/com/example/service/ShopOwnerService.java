package com.example.service;

import com.example.model.Shop;
import java.util.List;

import com.example.model.ShopOwner;

public interface ShopOwnerService {
    
    public void addShopOwner(ShopOwner shopOwner);
    
    public void updateShopOwner(ShopOwner shopOwner);
    
    public void deleteShopOwner(String name);
    
    public ShopOwner getShopOwnerByName(String name);
    
    public List<ShopOwner> getAllShopOwners();
    
    public void addShopToOwner(ShopOwner shopOwner, Shop shop);
    
    public void removeShopFromOwner(ShopOwner shopOwner, Shop shop);
    
}
