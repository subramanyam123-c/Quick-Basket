package com.example.dao;

import com.example.model.Category;
import java.util.List;

public interface CategoryDAO {
    public void save(Category category);
    public void update(Category category);
    public void delete(Category category);
    public Category getById(Long id);
    public List<Category> getAll();
}
