package com.qba.app.tests.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import com.qba.app.dao.CategoryRepo;
import com.qba.app.dao.ItemRepo;
import com.qba.app.dao.OrderRepo;
import com.qba.app.dao.StoreRepo;
import com.qba.app.model.Category;
import com.qba.app.model.Item;
import com.qba.app.model.Order;
import com.qba.app.model.Store;
import com.qba.app.service.StoreOwnerServiceImpl;

import java.util.ArrayList;
import java.util.List;

//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class StoreOwnerServiceTest {

    @InjectMocks
    private StoreOwnerServiceImpl adminService;

    @Mock
    private StoreRepo storeRepo;

    @Mock
    private CategoryRepo categoryRepo;

    @Mock
    private ItemRepo itemRepo;
    
    @Mock
    private OrderRepo orderRepo;

    
    @Test
    public void getAllStoresTest() {

        Store store = new Store();
        
        store.setName("storename");
        store.setAddress("address");
        store.setContact("conatct");
        store.setId(1L);
        store.setZipCode("1234");
        
        List<Store> stores = new ArrayList<>();
        stores.add(store);
        
        when(storeRepo.findAll()).thenReturn(stores);

        assertEquals(stores, adminService.getAllStores());

    }
    
    @Test
    public void getAllStoresNegativeTest() {

    	 Store store = new Store();
         
         store.setName("storename");
         store.setAddress("address");
         store.setContact("conatct");
         store.setId(1L);
         store.setZipCode("1234");
         
         List<Store> stores = new ArrayList<>();
         
         
         when(storeRepo.findAll()).thenReturn(stores);

         assertEquals(0, adminService.getAllStores().size());

    }
    
    @Test
    public void getAllCategoryTest() {

        Category category = new Category();
        
        category.setName("storename");
        category.setId(1L);
        
        List<Category> categories = new ArrayList<>();
        categories.add(category);
        
        when(categoryRepo.findAll()).thenReturn(categories);

        assertEquals(categories, adminService.getAllCategories());

    }
    
    @Test
    public void getAllCategoriesNegativeTest() {


        Category category = new Category();
        
        category.setName("storename");
        category.setId(1L);
        
        List<Category> categories = new ArrayList<>();
   
         
        when(categoryRepo.findAll()).thenReturn(categories);

        assertEquals(0, adminService.getAllCategories().size());

    }
    
    @Test
    public void getAllItemTest() {

        Item item = new Item();
        
        item.setCategory("fruit");
        item.setDescription("description");
        item.setName("banana");
        
        List<Item> items = new ArrayList<>();
        items.add(item);
        
        when(itemRepo.findAll()).thenReturn(items);

        assertEquals(items, adminService.getAllItems());

    }
    
    @Test
    public void getAllItemNegativeTest() {


    	Item item = new Item();
        
        item.setCategory("fruit");
        item.setDescription("description");
        item.setName("banana");
        
        List<Item> items = new ArrayList<>();
        
        
        when(itemRepo.findAll()).thenReturn(items);

        assertEquals(0, adminService.getAllItems().size());

    }
    
    @Test
    public void getItemByIdTest() {

        Item item = new Item();
        
        item.setCategory("fruit");
        item.setDescription("description");
        item.setName("banana");
        item.setId(1L);
        
        List<Item> items = new ArrayList<>();
        items.add(item);
        
        when(itemRepo.findItemById(1L)).thenReturn(item);

        assertEquals(item, adminService.getItemById(1L));

    }
    
    @Test
    public void getItemByIdNegativeTest() {


Item item = new Item();
        
        item.setCategory("fruit");
        item.setDescription("description");
        item.setName("banana");
        item.setId(1L);
   
        
        when(itemRepo.findItemById(1L)).thenReturn(null);

        assertEquals(null, adminService.getItemById(1L));
    }
    
    @Test
    public void getCategoryByIdTest() {

        Category category = new Category();
        
        category.setId(1L);
        category.setName("fruit");
        
        
        
        when(categoryRepo.findCategoryById(1L)).thenReturn(category);

        assertEquals(category, adminService.getCategoryById(1L));

    }
    
    @Test
    public void getCategoryByIdNegativeTest() {


    	 Category category = new Category();
         
         category.setId(1L);
         category.setName("fruit");
         
         
         
         when(categoryRepo.findCategoryById(1L)).thenReturn(null);

         assertEquals(null, adminService.getCategoryById(1L));
    }
    
    
    @Test
    public void getAllOrdersTest() {

        Order order = new Order();
        
        order.setName("storename");
        order.setEmail("email@gmail.com");
        
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        
        when(orderRepo.findAll()).thenReturn(orders);

        assertEquals(orders, adminService.getOrders());

    }
    
    @Test
    public void getAllOrdersNegativeTest() {

    	 Order order = new Order();
         
         order.setName("storename");
         order.setEmail("email@gmail.com");
         
         List<Order> orders = new ArrayList<>();
        
         
         when(orderRepo.findAll()).thenReturn(orders);

         assertEquals(0, adminService.getOrders().size());

    }
    
    
   
    
    

}