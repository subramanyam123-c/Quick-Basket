package com.qba.app.tests.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import com.qba.app.dao.RequestRepo;
import com.qba.app.dao.TicketRepo;
import com.qba.app.dao.UserRepo;
import com.qba.app.model.Ticket;
import com.qba.app.model.User;
import com.qba.app.service.AdminServiceImpl;

import java.util.ArrayList;
import java.util.List;

//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {

    @InjectMocks
    private AdminServiceImpl adminService;

    @Mock
    private UserRepo userRepo;

    @Mock
    private TicketRepo ticketRepo;

    @Mock
    private RequestRepo requestRepo;

    
    @Test
    public void unapprovedTicketTest() {

        Ticket ticket = new Ticket();
        
        ticket.setDescription("description");
        ticket.setEmail("user@gmail.com");
        ticket.setIsResolved("0");
        ticket.setSolution("solution");
        
        List<Ticket> tcks = new ArrayList<>();
        tcks.add(ticket);
        
        when(ticketRepo.findAll()).thenReturn(tcks);

        assertEquals(tcks, adminService.getAllUnApprovedTickets());

    }
    
    @Test
    public void unapprovedTicketNegativeTest() {

        Ticket ticket = new Ticket();
        
        ticket.setDescription("description");
        ticket.setEmail("user@gmail.com");
        ticket.setIsResolved("1");
        ticket.setSolution("solution");
        
        List<Ticket> tcks = new ArrayList<>();
        tcks.add(ticket);
        
        when(ticketRepo.findAll()).thenReturn(tcks);

        assertEquals(0, adminService.getAllUnApprovedTickets().size());

    }
    
    
    
   
    
    

}