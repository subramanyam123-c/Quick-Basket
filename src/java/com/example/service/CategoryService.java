package com.example.service;

import com.example.model.Category;
import java.util.List;

public interface CategoryService {
    public void addCategory(Category category);
    public void updateCategory(Category category);
    public void deleteCategory(Category category);
    public Category getCategoryById(Long id);
    public List<Category> getAllCategories();
}
