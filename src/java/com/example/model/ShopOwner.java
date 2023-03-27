package com.example.model;

import java.util.ArrayList;


public class ShopOwner {
    
    private String name;
    private ArrayList<Shop> shops;
    
    public ShopOwner() {
        
    }
    
    public ShopOwner(String name, ArrayList<Shop> shops) {
        this.name = name;
        this.shops = shops;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Shop> getShops() {
        return shops;
    }

    public void setShops(ArrayList<Shop> shops) {
        this.shops = shops;
    }
    
    public void addShop(Shop shop) {
        shops.add(shop);
    }
    
    public void removeShop(Shop shop) {
        shops.remove(shop);
    }
    
}
