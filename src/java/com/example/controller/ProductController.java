package com.example.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.model.Product;
import com.example.service.ProductService;
import com.example.service.ProductServiceImpl;
//import com.google.gson.Gson;

@WebServlet("/products/*")
public class ProductController extends HttpServlet {
    
    private ProductService productService;
    
    public ProductController() {
        productService = new ProductServiceImpl();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            List<Product> products = productService.getAllProducts();
            request.setAttribute("products", products);
            request.getRequestDispatcher("/WEB-INF/views/product/list.jsp").forward(request, response);
        } else {
            int id = Integer.parseInt(pathInfo.substring(1));
            Product product = productService.getProductById(id);
            //Gson gson = new Gson();
            response.setContentType("application/json");
            //response.getWriter().print(gson.toJson(product));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String status = request.getParameter("status");
        int shopId = Integer.parseInt(request.getParameter("shopId"));
        
        Product product = new Product(name, description, price, quantity, status, shopId);
        productService.addProduct(product);
        
        response.sendRedirect(request.getContextPath() + "/products/");
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String status = request.getParameter("status");
        int shopId = Integer.parseInt(request.getParameter("shopId"));
        
        Product product = new Product(id, name, description, price, quantity, status, shopId);
        productService.updateProduct(product);
        
        response.sendRedirect(request.getContextPath() + "/products/");
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        productService.deleteProduct(id);
        response.sendRedirect(request.getContextPath() + "/products/");
    }
}
