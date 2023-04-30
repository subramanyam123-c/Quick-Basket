package com.qba.app.controller;

import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.qba.app.model.Category;
import com.qba.app.model.Item;
import com.qba.app.model.Order;
import com.qba.app.model.Request;
import com.qba.app.model.Store;
import com.qba.app.model.Ticket;
import com.qba.app.model.User;
import com.qba.app.service.AdminService;
import com.qba.app.service.StoreOwnerService;
import com.qba.app.service.UserService;


@Controller
public class StoreOwnerController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private StoreOwnerService storeOwnerService;
	
	@Autowired
	private AdminService adminService;
	
	
	
	@GetMapping("/storeowner")
	public String getStoreOwnerWelcomePage(@ModelAttribute("user") User user, Model model, HttpSession session)
	{
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
        User userdata = userService.findUser(messages.get(0));
        List<Item> items =storeOwnerService.getAllItems();
        
        model.addAttribute("items", items);
		return "storeowner/welcomeowner";
	}
	
	@GetMapping("/stores")
	public String getStoresPage(Model model, HttpSession session)
	{
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
        User userdata = userService.findUser(messages.get(0));
        List<Store> stores = storeOwnerService.getAllStores();
       
        model.addAttribute("stores", stores);
		return "storeowner/stores";
	}
	
	@GetMapping("/addStore")
	public String getAddStorePage(Model model, HttpSession session)
	{
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
        User userdata = userService.findUser(messages.get(0));
        Store store = new Store();
        model.addAttribute("store", store);
		return "storeowner/addstore";
	}
	
	@PostMapping("/saveStore")
	public String saveStore(@ModelAttribute("store") Store store, HttpSession session, Model model )
	{
		System.out.println("raised Ticket");
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
		storeOwnerService.saveStore(store);
		return "redirect:/stores";
	}
	
	@PostMapping("/deleteStore/{id}")
	public String deleteStore(@PathVariable(name="id") Long id)
	{
		storeOwnerService.deleteStore(id);
		
		return "redirect:/stores";
	}
	
	@GetMapping("/categories")
	public String getCategoriesPage(Model model, HttpSession session)
	{
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
        User userdata = userService.findUser(messages.get(0));
        List<Category> categories = storeOwnerService.getAllCategories();
        model.addAttribute("categories", categories);
		return "storeowner/categories";
	}
	
	@GetMapping("/addCategory")
	public String getAddCategoryPage(Model model, HttpSession session)
	{
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
        User userdata = userService.findUser(messages.get(0));
        Category category = new Category();
        model.addAttribute("category", category);
		return "storeowner/addcategory";
	}
	
	@PostMapping("/saveCategory")
	public String saveCategory(@ModelAttribute("category") Category category, HttpSession session, Model model )
	{
		System.out.println("raised Ticket");
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
		storeOwnerService.saveCategory(category);
		return "redirect:/categories";
	}
	
	@GetMapping("/editCategory/{id}")
	public String editCategory(Model model, HttpSession session, @PathVariable(name="id") Long id) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
		Category category= storeOwnerService.getCategoryById(id);
		
		
		model.addAttribute("category", category);
		
		model.addAttribute("role", userdata.getUsertype());

		return "storeowner/editcategory";
	}
	
	@PostMapping("/updateCategory")
	public String updateCategory(@ModelAttribute("category") Category category, Model model, HttpSession session)
	{
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
		
		storeOwnerService.updateCategory(category);
		
		
		return "redirect:/categories";
	}
	
	
	@PostMapping("/deleteCategory/{id}")
	public String deleteCategory(@PathVariable(name="id") Long id)
	{
		storeOwnerService.deleteCategory(id);
		
		return "redirect:/categories";
	}
	
	@GetMapping("/items")
	public String getItemsPage(Model model, HttpSession session)
	{
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
        User userdata = userService.findUser(messages.get(0));
        List<Item> items = storeOwnerService.getAllItems();
        model.addAttribute("items", items);
		return "storeowner/items";
	}
	
	@GetMapping("/addItem")
	public String getAddItemPage(Model model, HttpSession session)
	{
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
        User userdata = userService.findUser(messages.get(0));
        Item item = new Item();
        model.addAttribute("item", item);
        List<Category> categories = storeOwnerService.getAllCategories();
        model.addAttribute("categories", categories);
		return "storeowner/additem";
	}
	
	@PostMapping("/saveItem")
	public String saveItem(@ModelAttribute("item") Item item, HttpSession session, Model model,  @RequestParam("image") MultipartFile itemImage )
	{
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
		
		try {			

			item.setItemPhoto(Base64.getEncoder().encodeToString(itemImage.getBytes()));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		item.setStatus("1");
		storeOwnerService.saveItem(item);
		return "redirect:/items";
	}
	
	@GetMapping("/editItem/{id}")
	public String editItem(Model model, HttpSession session, @PathVariable(name="id") Long id) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
		Item item= storeOwnerService.getItemById(id);
		
		
		model.addAttribute("item", item);
		
		model.addAttribute("role", userdata.getUsertype());
		 List<Category> categories = storeOwnerService.getAllCategories();
	        model.addAttribute("categories", categories);

		return "storeowner/edititem";
	}
	
	@PostMapping("/updateItem")
	public String updateItem(@ModelAttribute("item") Item item, Model model, HttpSession session,  @RequestParam("image") MultipartFile itemImage )
	{
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
		try {			

			item.setItemPhoto(Base64.getEncoder().encodeToString(itemImage.getBytes()));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		storeOwnerService.updateItem(item);
		
		
		return "redirect:/items";
	}
	
	@PostMapping("/deleteItem/{id}")
	public String deleteItem(@PathVariable(name="id") Long id)
	{
		storeOwnerService.deleteItem(id);
		
		return "redirect:/items";
	}
	
	@GetMapping("/requests")
	public String getRequestsPage(Model model, HttpSession session)
	{
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
        User userdata = userService.findUser(messages.get(0));
        List<Request> requests = userService.getAllUnApprovedRequests();
        model.addAttribute("requests", requests);
		return "storeowner/requests";
	}
	
	@GetMapping("/approveRequest/{id}")
	public String approveRequest(@PathVariable(name="id") Long id)
	{
		adminService.approveRequest(id);
		
		return "redirect:/storeowner";
	}
	
	
	
	@GetMapping("/rejectRequest/{id}")
	public String rejectRequest(@PathVariable(name="id") Long id)
	{
		adminService.rejectRequest(id);
		
		return "redirect:/storeowner";
	}
	
	@GetMapping("/orderstatus")
	public String orderstatus(Model model, HttpSession session)
	{
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
        User userdata = userService.findUser(messages.get(0));

        List<Order> orders = userService.getAllOrders();
        if(orders.size() == 0) {
        	model.addAttribute("info", "No Orders Now");
        }else {
        	model.addAttribute("info", "");
        }
		model.addAttribute("orders", userService.getAllOrders());

		model.addAttribute("role", userdata.getUsertype());
        
		return "storeowner/orderstatus";
	}
	
	@PostMapping("/updateOrderStatus/{id}")
	public String updateOrderStatus(@PathVariable(name="id") Long id, @RequestParam("status") String status)
	{
		storeOwnerService.updateOrderStatus(status, id);
		System.out.println(status);
		return "redirect:/storeowner";
	}
	
	@PostMapping("/searchOnwerItems")
	public String searchOnwerItems(Model model, HttpSession session, @RequestParam("searchKey") String searchKey ) {
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
        model.addAttribute("sessionMessages", messages);
        List<Item> items = userService.searchItems(searchKey, userdata.getEmail());
        model.addAttribute("items", items);
        model.addAttribute("role", userdata.getUsertype());
		return "storeowner/welcomeowner";
	}
	

	@GetMapping("/generate")
	public String generate( Model model)
	{
		List<Category> categories = storeOwnerService.getAllCategories();
		
		List<Item> items = storeOwnerService.getAllItems();
		
		List<Order> orders = storeOwnerService.getOrders();
		
		List<User> users = userService.getAllUsers();
		
		List<Store> stores = storeOwnerService.getAllStores();
		
		model.addAttribute("categories", String.valueOf(categories.size()));
		
		model.addAttribute("items", String.valueOf(items.size()));
		model.addAttribute("orders", String.valueOf(orders.size()));
		model.addAttribute("users", String.valueOf(users.size()));
		model.addAttribute("stores", String.valueOf(stores.size()));
		
		
		return "storeowner/generate";
	}
}
