package com.example.service.impl;

import com.example.dao.CategoryDAO;
import com.example.model.Category;
import com.example.service.CategoryService;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private CategoryDAO categoryDAO;

    public CategoryServiceImpl(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    public void addCategory(Category category) {
        categoryDAO.save(category);
    }

    @Override
    public void updateCategory(Category category) {
        categoryDAO.update(category);
    }

    @Override
    public void deleteCategory(Category category) {
        categoryDAO.delete(category);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryDAO.getById(id);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryDAO.getAll();
    }
}
