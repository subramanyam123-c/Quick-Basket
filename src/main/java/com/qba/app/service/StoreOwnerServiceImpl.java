package com.qba.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qba.app.dao.CategoryRepo;
import com.qba.app.dao.ItemRepo;
import com.qba.app.dao.OrderRepo;
import com.qba.app.dao.StoreRepo;
import com.qba.app.model.Category;
import com.qba.app.model.Item;
import com.qba.app.model.Store;

@Service
public class StoreOwnerServiceImpl implements StoreOwnerService{
	
	@Autowired
	private StoreRepo storeRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ItemRepo itemRepo;
	
	@Autowired
	private OrderRepo orderRepo;


	@Override
	public List<Store> getAllStores() {
		// TODO Auto-generated method stub
		return storeRepo.findAll();
	}

	@Override
	public void saveStore(Store store) {
		// TODO Auto-generated method stub
		storeRepo.save(store);
	}

	@Override
	public void deleteStore(Long id) {
		// TODO Auto-generated method stub
		storeRepo.delete(storeRepo.findStoreById(id));
	}

	@Override
	public List<Category> getAllCategories() {
		// TODO Auto-generated method stub
		return categoryRepo.findAll();
	}

	@Override
	public void saveCategory(Category category) {
		// TODO Auto-generated method stub
		categoryRepo.save(category);
		
	}

	@Override
	public void deleteCategory(Long id) {
		// TODO Auto-generated method stub
		categoryRepo.delete(categoryRepo.findCategoryById(id));
	}

	@Override
	public List<Item> getAllItems() {
		// TODO Auto-generated method stub
		return itemRepo.findAll();
	}

	@Override
	public void saveItem(Item item) {
		// TODO Auto-generated method stub
		itemRepo.save(item);
		
	}

	@Override
	public void deleteItem(Long id) {
		// TODO Auto-generated method stub
		itemRepo.delete(itemRepo.findItemById(id));
		
	}

	@Override
	public Item getItemById(Long id) {
		// TODO Auto-generated method stub
		return itemRepo.findItemById(id);
	}

	@Override
	public void updateItem(Item item) {
		// TODO Auto-generated method stub
		itemRepo.save(item);
		
	}

	@Override
	public Category getCategoryById(Long id) {
		// TODO Auto-generated method stub
		return categoryRepo.findCategoryById(id);
	}

	@Override
	public void updateCategory(Category category) {
		// TODO Auto-generated method stub
		categoryRepo.save(category);
	}

	@Override
	public void updateOrderStatus(String status, Long id) {
		// TODO Auto-generated method stub
		System.out.println("==="+status);
		orderRepo.updateOrderStatus(status, id);
		
	}

}
