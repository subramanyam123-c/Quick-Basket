package com.example.dao;

import com.example.model.Cart;

public interface CartDao {
    
    public void addCart(Cart cart);
    
    public void updateCart(Cart cart);
    
    public void deleteCart(Cart cart);
    
    public Cart getCartById(int id);
    
}
