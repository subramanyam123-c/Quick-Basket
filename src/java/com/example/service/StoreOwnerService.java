package com.qba.app.service;

import java.util.List;

import com.qba.app.model.Category;
import com.qba.app.model.Item;
import com.qba.app.model.Store;

public interface StoreOwnerService {

	List<Store> getAllStores();

	void saveStore(Store store);

	void deleteStore(Long id);

	List<Category> getAllCategories();

	void saveCategory(Category category);

	void deleteCategory(Long id);

	List<Item> getAllItems();

	void saveItem(Item item);

	void deleteItem(Long id);

	Item getItemById(Long id);

	void updateItem(Item item);

	Category getCategoryById(Long id);

	void updateCategory(Category category);

}
