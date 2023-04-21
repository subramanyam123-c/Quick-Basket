package com.example.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.model.Cart;
import com.example.model.CartItem;
import com.example.service.CartService;
import com.example.service.CartPriceCalculator;
import com.example.service.FlatCartPriceCalculator;
import com.example.service.DiscountedCartPriceCalculator;
import java.io.IOException;
import javax.servlet.ServletException;

public class CartController {
    
    private CartService cartService;
    
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    
    public void viewCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int cartId = Integer.parseInt(request.getParameter("id"));
        Cart cart = cartService.getCartById(cartId);
        request.setAttribute("cart", cart);
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }
    
    public void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String itemName = request.getParameter("name");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double price = Double.parseDouble(request.getParameter("price"));
        CartItem item = new CartItem(itemName, quantity, price);
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }
        cart.addItem(item);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
    
    public void checkout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        CartPriceCalculator priceCalculator = null;
        String priceType = request.getParameter("priceType");
        if (priceType.equals("flat")) {
            priceCalculator = new FlatCartPriceCalculator(10);
        } else if (priceType.equals("discount")) {
            priceCalculator = new DiscountedCartPriceCalculator(20);
        }
        double totalPrice = cart.getTotalPrice(priceCalculator);
        request.setAttribute("totalPrice", totalPrice);
        request.getRequestDispatcher("checkout.jsp").forward(request, response);
    }
    
    public void placeOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        cartService.addCart(cart);
        request.getSession().removeAttribute("cart");
        request.getRequestDispatcher("orderConfirmation.jsp").forward(request, response);
    }
    
}
