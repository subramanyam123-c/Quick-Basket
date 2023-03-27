package com.example.controller;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.model.Shop;
import com.example.model.ShopOwner;
import com.example.model.ZipCode;
import com.example.service.ShopOwnerService;
import com.example.service.impl.ShopOwnerServiceImpl;
import java.io.IOException;
import javax.servlet.ServletException;

public class ShopOwnerController {
    
    private ShopOwnerService shopOwnerService;
    
    public ShopOwnerController() {
        shopOwnerService = new ShopOwnerServiceImpl();
    }
    
    public void addShopOwner(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        ShopOwner shopOwner = new ShopOwner();
        shopOwner.setName(name);
        shopOwnerService.addShopOwner(shopOwner);
        RequestDispatcher rd = request.getRequestDispatcher("/listShopOwners");
        rd.forward(request, response);
    }
    
    public void showAddShopOwnerForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher rd = request.getRequestDispatcher("addShopOwner.jsp");
        try {
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void listShopOwners(HttpServletRequest request, HttpServletResponse response) {
        List<ShopOwner> shopOwners = shopOwnerService.getAllShopOwners();
        request.setAttribute("shopOwners", shopOwners);
        RequestDispatcher rd = request.getRequestDispatcher("listShopOwners.jsp");
        try {
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void deleteShopOwner(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        shopOwnerService.deleteShopOwner(name);
        RequestDispatcher rd = request.getRequestDispatcher("/listShopOwners");
        try {
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void showUpdateShopOwnerForm(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        ShopOwner shopOwner = shopOwnerService.getShopOwnerByName(name);
        request.setAttribute("shopOwner", shopOwner);
        RequestDispatcher rd = request.getRequestDispatcher("updateShopOwner.jsp");
        try {
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void updateShopOwner(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        ShopOwner shopOwner = new ShopOwner();
        shopOwner.setName(name);
        shopOwnerService.updateShopOwner(shopOwner);
        RequestDispatcher rd = request.getRequestDispatcher("/listShopOwners");
        try {
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void showAddShopForm(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        ShopOwner shopOwner = shopOwnerService.getShopOwnerByName(name);
        request.setAttribute("shopOwner", shopOwner);
        RequestDispatcher rd = request.getRequestDispatcher("addShop.jsp");
        try {
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void addShop(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String zipcode = request.getParameter("zipcode");
        boolean status = Boolean.parseBoolean(request.getParameter("status"));
        String ownerName = request.getParameter("ownerName");
        ShopOwner shopOwner = shopOwnerService.getShopOwnerByName(ownerName);
        Shop shop = new Shop();
        shop.setName(name);
        shop.setZipCode(new ZipCode(zipcode));
        shop.setStatus(status);
    }
    
}