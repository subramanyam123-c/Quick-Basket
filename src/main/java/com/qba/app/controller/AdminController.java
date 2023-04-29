package com.qba.app.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.qba.app.model.Request;
import com.qba.app.model.Ticket;
import com.qba.app.model.User;
import com.qba.app.service.AdminService;
import com.qba.app.service.UserService;

@Controller
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AdminService adminService;
	
	
	
	@GetMapping("/admin")
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
		return "admin/welcomeadmin";
	}
	
	@GetMapping("/grantpage")
	public String getGrantPermissionPage(@ModelAttribute("user") User user, Model model, HttpSession session)
	{
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
        User userdata = userService.findUser(messages.get(0));
        List<User> users = userService.getAllUnApprovedUsers();
        model.addAttribute("users", users);
		return "admin/grantpage";
	}
	

	
	@GetMapping("/tickets")
	public String getTicketsPage(Model model, HttpSession session)
	{
		@SuppressWarnings("unchecked")
        List<String> messages = (List<String>) session.getAttribute("MY_SESSION_MESSAGES");

		if(messages == null) {
			model.addAttribute("errormsg", "Session Expired. Please Login Again");
			return "home/error";
		}
        model.addAttribute("sessionMessages", messages);
        User userdata = userService.findUser(messages.get(0));
        List<Ticket> tickets = adminService.getAllUnApprovedTickets();
        model.addAttribute("tickets", tickets);
		return "admin/tickets";
	}
	
	@GetMapping("/approveUser/{id}")
	public String approveUser(Model model, @PathVariable(name="id") Long id) {
		
		
		adminService.approveUser(id);
		
		
		return "redirect:/admin";
		
	}
	

	
	@GetMapping("/resolveTicket/{id}")
	public String resolveTicket(@PathVariable(name="id") Long id)
	{
		adminService.resolveTicket(id);
		
		return "redirect:/admin";
	}
	
	
}
