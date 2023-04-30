package com.qba.app.controller;

import java.util.List;
import java.util.StringJoiner;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.qba.app.model.Cart;
import com.qba.app.model.Category;
import com.qba.app.model.Item;
import com.qba.app.model.Order;
import com.qba.app.model.Recommendation;
import com.qba.app.model.Request;
import com.qba.app.model.Review;
import com.qba.app.model.Reward;
import com.qba.app.model.Ticket;
import com.qba.app.model.User;
import com.qba.app.service.StoreOwnerService;
import com.qba.app.service.UserService;


@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private StoreOwnerService storeOwnerService;
	
	
	
	@GetMapping("/user")
	public String getCustomerWelcomePage(@ModelAttribute("user") User user, Model model, HttpSession session)
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
        
        List<Category> categories = storeOwnerService.getAllCategories();
        model.addAttribute("categories", categories);
        
		return "user/welcomeuser";
	}
	
	@GetMapping("/raiseTicket")
	public String ticket(Model model, HttpSession session) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
		Ticket ticket = new Ticket();
		model.addAttribute("ticket", ticket);
		model.addAttribute("userMail", userdata.getEmail());
		model.addAttribute("role", userdata.getUsertype());
		if(userdata.getUsertype().equals("user"))
		return "user/raiseticket";
		else
			return "storeowner/raiseticket";
	}
	
	@PostMapping("/ticketRaise")
	public String raiseTicket(@ModelAttribute("ticket") Ticket ticket, HttpSession session, Model model )
	{
		System.out.println("raised Ticket");
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
		ticket.setEmail(userdata.getEmail());
		ticket.setIsResolved("0");
		ticket.setSolution("");
		userService.saveTicket(ticket);
		if(userdata.getUsertype().equals("user"))
		return "redirect:/user";
		else
			return "redirect:/storeowner";
	}
	
	@GetMapping("/itemRequest")
	public String itemRequest(Model model, HttpSession session) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
		Request request = new Request();
		model.addAttribute("request", request);
		model.addAttribute("userMail", userdata.getEmail());

		List<Category> categories = storeOwnerService.getAllCategories();
		model.addAttribute("categories", categories);
		model.addAttribute("role", userdata.getUsertype());
		
		return "user/requestitem";
		
	}
	
	@PostMapping("/requestItem")
	public String requestItem(@ModelAttribute("request") Request request, HttpSession session, Model model )
	{
		System.out.println("raised Ticket");
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
		request.setIsApproved("0");
		request.setEmail(userdata.getEmail());
		userService.saveRequest(request);
		return "redirect:/user";
		
	}
	
	
	@GetMapping("/review")
	public String review(Model model, HttpSession session) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
		Review review = new Review();
		model.addAttribute("review", review);
		model.addAttribute("userMail", userdata.getEmail());

		model.addAttribute("role", userdata.getUsertype());
		
		return "user/review";
		
	}
	
	@PostMapping("/reviewNow")
	public String reviewNow(@ModelAttribute("review") Review review, HttpSession session, Model model )
	{
		System.out.println("raised Ticket");
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
		
		review.setEmail(userdata.getEmail());
		userService.saveReview(review);
		return "redirect:/user";
		
	}
	
	@PostMapping("/addToCart/{id}")
	public String addToCart(@PathVariable(name="id") Long id,@ModelAttribute("cart") Cart cart,HttpSession session, Model model )
	{
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
		int totalCost = Integer.parseInt(cart.getQuantity()) * Integer.parseInt(cart.getPrice()) - (Integer.parseInt(cart.getDiscount()) * Integer.parseInt(cart.getQuantity()));
		cart.setTotalCost(String.valueOf(totalCost));
		cart.setEmail(userdata.getEmail());
		userService.addToCart(cart);
		
		return "redirect:/user";
	}
	
	
	@GetMapping("/cart")
	public String cart(Model model, HttpSession session) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
		List<Cart> carts = userService.getUserCart(userdata.getEmail());
		model.addAttribute("carts", carts);
		String rewards = userService.getRewards(userdata.getEmail());
		int finalPrice = 0;
		for(int i=0;i<carts.size();i++) {
			finalPrice = finalPrice + Integer.parseInt(carts.get(i).getTotalCost());
		}
		finalPrice = finalPrice - Integer.parseInt(rewards);
		model.addAttribute("rewards", rewards);
		model.addAttribute("finalprice", finalPrice);
		model.addAttribute("cartsize", carts.size());
		model.addAttribute("role", userdata.getUsertype());
		
		return "user/cart";
		
	}
	

	@PostMapping("/removeFromCart/{id}")
	public String removeFromCart(@PathVariable(name="id") Long id)
	{
		userService.deleteFromCart(id);
		
		return "user/cart";
	}
	
	
	@GetMapping("/checkout")
	public String checkout(Model model, HttpSession session) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
		
		Order order = new Order();

		model.addAttribute("role", userdata.getUsertype());
		model.addAttribute("order", order);
		
		return "user/order";
		
	}
	
	@PostMapping("orderNow")
	public String orderNow(@ModelAttribute("order") Order order,HttpSession session, Model model )
	{
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
		
		List<Cart> cart =userService.getUserCart(userdata.getEmail());
		
		StringJoiner name = new StringJoiner(",");
		StringJoiner price = new StringJoiner(",");
		StringJoiner quantity = new StringJoiner(",");
		StringJoiner discount = new StringJoiner(",");
		StringJoiner totalCost = new StringJoiner(",");
		
		 
		int finalCost = 0;
		
		for(int i=0;i<cart.size(); i++) {
			
			name.add(cart.get(i).getName());
			
			price.add(cart.get(i).getPrice());
			
			quantity.add(cart.get(i).getQuantity());
			
			discount.add(cart.get(i).getDiscount());
			
			totalCost.add(cart.get(i).getTotalCost());
			
			finalCost = finalCost + Integer.parseInt(cart.get(i).getTotalCost());
		}
		
		order.setName(name.toString());
		order.setPrice(price.toString());
		order.setQuantity(quantity.toString());
		order.setDiscount(discount.toString());
		order.setTotalCost(totalCost.toString());
		order.setEmail(userdata.getEmail());
		order.setFinalBill(String.valueOf(finalCost));
		order.setStatus("ordered");
		
		Reward reward = new Reward();
		
		reward.setEmail(userdata.getEmail());
		reward.setPoints(String.valueOf(finalCost/2));
		
		userService.saveOrder(order, userdata.getEmail(), reward);
		
		
		
		
		return "redirect:/user";
	}
	
	
	@PostMapping("/searchItems")
	public String searchItems(Model model, HttpSession session, @RequestParam("searchKey") String searchKey ) {
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
		return "user/welcomeuser";
	}
	

	@PostMapping("/applyFilters")
	public String applyFilters(Model model, HttpSession session, @RequestParam("category") String category) {
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
        model.addAttribute("sessionMessages", messages);
        List<Item> items = userService.filterItems(category);
        model.addAttribute("items", items);
        model.addAttribute("role", userdata.getUsertype());
		return "user/welcomeuser";
	}
	
	@GetMapping("/orders")
	public String orders(Model model, HttpSession session) {
		
		
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");
		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
		User userdata = userService.findUser(messages.get(0));
		
		model.addAttribute("orders", userService.getUserOrders(userdata.getEmail()));

		model.addAttribute("role", userdata.getUsertype());
		
		return "user/orders";
		
	}
	
	@GetMapping("/recommendations")
	public String recommendations( Model model, HttpSession session)
	{
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
        User userdata = userService.findUser(messages.get(0));
        List<Recommendation> items =userService.getAllRecommendations(userdata.getEmail());
        
        model.addAttribute("items", items);
       
        
		return "user/recommendations";
	}
	
}
