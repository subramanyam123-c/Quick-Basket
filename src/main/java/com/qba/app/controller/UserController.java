package com.qba.app.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.qba.app.model.Ticket;
import com.qba.app.model.User;
import com.qba.app.service.UserService;


@Controller
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	
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
}
