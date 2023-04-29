package com.qba.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qba.app.dao.CartRepo;
import com.qba.app.dao.ItemRepo;
import com.qba.app.dao.OrderRepo;
import com.qba.app.dao.RequestRepo;
import com.qba.app.dao.ReviewRepo;
import com.qba.app.dao.RewardRepo;
import com.qba.app.dao.TicketRepo;
import com.qba.app.dao.UserRepo;
import com.qba.app.model.Cart;
import com.qba.app.model.Item;
import com.qba.app.model.Order;
import com.qba.app.model.Request;
import com.qba.app.model.Review;
import com.qba.app.model.Reward;
import com.qba.app.model.Ticket;
import com.qba.app.model.User;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private TicketRepo ticketRepo;
	
	@Autowired
	private RequestRepo requestRepo;
	
	@Autowired
	private ReviewRepo reviewRepo;
	
	@Autowired
	private OrderRepo orderRepo;
	
	@Autowired
	private ItemRepo itemRepo;
	
	@Autowired
	private CartRepo cartRepo;
	
	@Autowired
	private RewardRepo rewardRepo;
	
	public int saveUser(User user) {
		userRepo.save(user);
		if(userRepo.save(user)!=null) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
	public User findUser(String email) {
		List<User> user = userRepo.findAll();
		System.out.println("----"+user.size());
		if(user.size() == 0) {
			return null;
		}
		List<User> veifiedUser = user.stream().filter(n -> n.getEmail().equals(email)).collect(Collectors.toList());
		if(veifiedUser.size() > 0) {
			return veifiedUser.get(0);
		}
		else {
			return null;
		}
		
	}
	
	public User authenticateUser(User user) {
		
		if(user.getEmail().equals("admin@gmail.com") && user.getPassword().equals("admin")) {
			
			user.setUsertype("admin");
			
			return user;
		}
		
		List<User> users = userRepo.findAll();
		List<User> veifiedUser = users.stream().filter(n -> (n.getEmail().equals(user.getEmail()) || n.getUsername().equals(user.getEmail())) && n.getPassword().equals(user.getPassword())).collect(Collectors.toList());
		
		if(veifiedUser.size() ==1) {
			return veifiedUser.get(0);
		}
		else {
			return null;
		}
			
	}
	
	public User findUserByUsername(String username) {
		
		List<User> users = userRepo.findAll();
		List<User> veifiedUser = users.stream().filter(n -> n.getUsername().equals(username)).collect(Collectors.toList());
		if(veifiedUser.size() > 0) {
			return veifiedUser.get(0);
		}
		else {
			return null;
		}
		
	}
	
	public int validatePassword(User usermodel, String securityQuestion, String securityAnswer) {
		List<User> users = userRepo.findAll();
		List<User> verifiedUser = users.stream().filter(n -> n.getEmail().equals(usermodel.getEmail())).collect(Collectors.toList());
		if(verifiedUser.size() ==1) {
			List<User> userSecurities = userRepo.findAll();
			
			List<User> securedUser = userSecurities.stream().filter(security -> security.getSecurityQuestion().equals(securityQuestion) && security.getSecurityAnswer().equals(securityAnswer)
					
					).collect(Collectors.toList());
			if(securedUser.size() == 1) {
				return 1;
			}
			else {
				return 2;
			}
		}
		else {
			return 0;
		}
	}
	
	public void saveNewPassword(User usermodel) {
			
			User user = userRepo.findbyEmail(usermodel.getEmail());
			System.out.println("user#########"+user.toString());
			user.setPassword(usermodel.getPassword());
			userRepo.save(user);
	}
	
	public void deleteUser(Long id) {
			
			userRepo.deleteById(id);
			
	}

	@Override
	public List<User> getAllUnApprovedUsers() {
		// TODO Auto-generated method stub
		return userRepo.findAll().stream().filter(u -> u.getIsApproved().equals("0")).collect(Collectors.toList());
	}

	@Override
	public void saveTicket(Ticket ticket) {
		// TODO Auto-generated method stub
		ticketRepo.save(ticket);
		
	}

	@Override
	public void saveRequest(Request request) {
		// TODO Auto-generated method stub
		requestRepo.save(request);
		
	}

	@Override
	public List<Request> getAllUnApprovedRequests() {
		// TODO Auto-generated method stub
		return requestRepo.findAll().stream().filter(r -> r.getIsApproved().equals("0")).collect(Collectors.toList());
	}

	@Override
	public void saveReview(Review review) {
		// TODO Auto-generated method stub
		reviewRepo.save(review);
		
	}

	@Override
	public void addToCart(Cart cart) {
		// TODO Auto-generated method stub
		cartRepo.save(cart);
		
	}

	@Override
	public List<Cart> getUserCart(String email) {
		// TODO Auto-generated method stub
		return cartRepo.findCartByEmail(email);
	}

	@Override
	public void deleteFromCart(Long id) {
		// TODO Auto-generated method stub
		cartRepo.delete(cartRepo.getCartById(id));
		
	}

	@Override
	public void saveOrder(Order order, String email, Reward reward) {
		// TODO Auto-generated method stub
		orderRepo.save(order);
		
		cartRepo.deleteCart(email);
		
		rewardRepo.save(reward);
		
		
		
	}
	
	
	public List<Item> searchItems(String searchKey) {
		// TODO Auto-generated method stub
		List<Item> items = itemRepo.findAll();
		List<Item> searchedItems = items.stream().filter(item -> item.getName().contains(searchKey) ||
				item.getCategory().equals(searchKey) || item.getDescription().contains(searchKey) || item.getZipCode().equals(searchKey)
				).collect(Collectors.toList());
		return searchedItems;
		
	}
	
	public List<Item> filterItems(String category) {
		// TODO Auto-generated method stub
		List<Item> items = itemRepo.findAll();
		List<Item> searchedItems = items.stream().filter(item -> item.getCategory().contains(category)).collect(Collectors.toList());
		return searchedItems;
		
	}

	@Override
	public Object getUserOrders(String email) {
		// TODO Auto-generated method stub
		return orderRepo.findOrderByEmail(email);
	}

	@Override
	public Object getAllOrders() {
		// TODO Auto-generated method stub
		System.out.println(orderRepo.findAll().get(0));
		return orderRepo.findAll();
	}

}
