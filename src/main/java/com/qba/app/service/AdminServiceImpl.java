package com.qba.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qba.app.dao.RequestRepo;
import com.qba.app.dao.TicketRepo;
import com.qba.app.dao.UserRepo;
import com.qba.app.model.Request;
import com.qba.app.model.Ticket;
import com.qba.app.model.User;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private TicketRepo ticketRepo;
	
	@Autowired
	private RequestRepo requestRepo;

	@Override
	public void approveUser(Long id) {
		// TODO Auto-generated method stub
		
		User user = userRepo.findUserById(id);
		user.setIsApproved("1");
		userRepo.save(user);
		
	}

	@Override
	public void approveRequest(Long id) {
		// TODO Auto-generated method stub
		Request request = requestRepo.getRequestById(id);
		request.setIsApproved("1");
		requestRepo.save(request);
	}

	@Override
	public void rejectRequest(Long id) {
		Request request = requestRepo.getRequestById(id);
		request.setIsApproved("2");
		requestRepo.save(request);
		
	}

	@Override
	public void resolveTicket(Long id) {
		// TODO Auto-generated method stub
		
		ticketRepo.delete(ticketRepo.findTicketsbyId(id));
		
	}

	@Override
	public List<Ticket> getAllUnApprovedTickets() {
		// TODO Auto-generated method stub
		
		return ticketRepo.findAll().stream().filter(t -> t.getIsResolved().equals("0")).collect(Collectors.toList());
	}
	
	
	
	
	

}
