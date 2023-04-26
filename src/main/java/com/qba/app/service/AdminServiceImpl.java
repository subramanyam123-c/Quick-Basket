package com.qba.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qba.app.dao.UserRepo;
import com.qba.app.model.User;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public void approveUser(Long id) {
		// TODO Auto-generated method stub
		
		User user = userRepo.findUserById(id);
		user.setIsApproved("1");
		userRepo.save(user);
		
	}
	
	
	
	
	

}
