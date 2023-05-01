package com.qba.app.service;

import java.util.List;

import com.qba.app.model.Cart;
import com.qba.app.model.Item;
import com.qba.app.model.Order;
import com.qba.app.model.Recommendation;
import com.qba.app.model.Request;
import com.qba.app.model.Review;
import com.qba.app.model.Reward;
import com.qba.app.model.Ticket;
import com.qba.app.model.User;

public interface UserService {

	User findUser(String email);

	User findUserByUsername(String username);

	int saveUser(User user);

	User authenticateUser(User user);

	int validatePassword(User user, String securityQuestion, String securityAnswer);

	void saveNewPassword(User user);

	void deleteUser(Long id);

	List<User> getAllUnApprovedUsers();

	void saveTicket(Ticket ticket);

	void saveRequest(Request request);

	List<Request> getAllUnApprovedRequests();

	void saveReview(Review review);

	void addToCart(Cart cart);

	List<Cart> getUserCart(String email);

	void deleteFromCart(Long id);

	void saveOrder(Order order, String email, Reward reward);

	List<Item> searchItems(String searchKey, String email);

	List<Item> filterItems(String category);

	Object getUserOrders(String email);

	List<Order> getAllOrders();

	List<Recommendation> getAllRecommendations(String email);

	String getRewards(String email);

	List<User> getAllUsers();

	Reward getReward(String email);


}
