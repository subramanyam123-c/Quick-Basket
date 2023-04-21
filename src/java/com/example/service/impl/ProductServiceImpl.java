package com.example.service;

import java.util.List;

import com.example.dao.ProductDao;
import com.example.dao.Impl.ProductDaoImpl;
import com.example.model.Product;

public class ProductServiceImpl implements ProductService {
    
    private ProductDao productDao;
    
    public ProductServiceImpl() {
        productDao = new ProductDaoImpl();
    }

    @Override
    public void addProduct(Product product) {
        // Add validation logic here
        productDao.addProduct(product);
    }

    @Override
    public void updateProduct(Product product) {
        // Add validation logic here
        productDao.updateProduct(product);
    }

    @Override
    public void deleteProduct(int id) {
        // Add validation logic here
        productDao.deleteProduct(id);
    }

    @Override
    public Product getProductById(int id) {
        return productDao.getProductById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }
}
