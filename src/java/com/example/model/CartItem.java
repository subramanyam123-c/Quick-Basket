package com.example.model;

public class CartItem {
    
    private String name;
    private int quantity;
    private double price;
    
    public CartItem(String name, int quantity, double price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
    
    public String getName() {
        return name;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
