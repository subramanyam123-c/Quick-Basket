package com.qba.app.service;

import java.util.List;

import com.qba.app.model.Ticket;

public interface AdminService {

	void approveUser(Long id);

	void approveRequest(Long id);

	void rejectRequest(Long id);

	void resolveTicket(Long id);

	List<Ticket> getAllUnApprovedTickets();

}
