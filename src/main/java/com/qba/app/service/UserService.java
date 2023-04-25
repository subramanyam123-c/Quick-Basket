package com.qba.app.service;

import java.util.List;

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

}
