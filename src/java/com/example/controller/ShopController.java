package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.model.Shop;
import com.example.service.ShopService;
import com.example.service.impl.ShopServiceImpl;
import com.example.model.ZipCode;

public class ShopController {
    
    private ShopService shopService;
    
    public ShopController() {
        shopService = new ShopServiceImpl();
    }
    
    public String addShop(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String code = request.getParameter("zipcode");
        boolean status = Boolean.valueOf(request.getParameter("status"));
        ZipCode zipCode = new ZipCode(code);
        Shop shop = new Shop(name, zipCode, status);
        shopService.addShop(shop);
        return "shop_list.jsp";
    }
    
    public String updateShop(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String code = request.getParameter("zipcode");
        boolean status = Boolean.valueOf(request.getParameter("status"));
        ZipCode zipCode = new ZipCode(code);
        Shop shop = new Shop(name, zipCode, status);
        shopService.updateShop(shop);
        return "shop_list.jsp";
    }
    
    public String deleteShop(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        shopService.deleteShop(name);
        return "shop_list.jsp";
    }
    
    public String getShopByName(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        Shop shop = shopService.getShopByName(name);
        request.setAttribute("shop", shop);
        return "shop_detail.jsp";
    }
    
    public String getAllShops(HttpServletRequest request, HttpServletResponse response) {
        List<Shop> shops = shopService.getAllShops();
        request.setAttribute("shops", shops);
        return "shop_list.jsp";
    }
    
}
