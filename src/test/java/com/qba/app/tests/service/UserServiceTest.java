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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import com.qba.app.dao.CartRepo;
import com.qba.app.dao.ItemRepo;
import com.qba.app.dao.OrderRepo;
import com.qba.app.dao.RecommendationRepo;
import com.qba.app.dao.RequestRepo;
import com.qba.app.dao.ReviewRepo;
import com.qba.app.dao.RewardRepo;
import com.qba.app.dao.TicketRepo;
import com.qba.app.dao.UserRepo;
import com.qba.app.model.Category;
import com.qba.app.model.Item;
import com.qba.app.model.Order;
import com.qba.app.model.Ticket;
import com.qba.app.model.User;
import com.qba.app.service.AdminServiceImpl;
import com.qba.app.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepo userRepo;

    @Mock
    private TicketRepo ticketRepo;

    @Mock
    private RequestRepo requestRepo;
    
    @Mock
    private ReviewRepo reviewRepo;
    
    @Mock
    private OrderRepo orderRepo;
    
    @Mock
    private ItemRepo itemRepo;
    
    @Mock
    private CartRepo cartRepo;
    
    @Mock
    private RewardRepo rewardRepo;
    
    @Mock
	private RecommendationRepo recommendationRepo;

    
    @Test
    public void saveUserTest() {

        User user = new User();

        user.setUsername("user");
        user.setEmail("user@gmail.com");
        user.setFirstname("fname");
        user.setLastname("lname");
        
        when(userRepo.save(user)).thenReturn(user);

        assertEquals(1,userService.saveUser(user));

    }
    
    @Test
    public void saveUserExceptionTest() {

    	 User user = new User();

         user.setUsername("user");
         user.setEmail("user@gmail.com");
         user.setFirstname("fname");
         user.setLastname("lname");
         
         when(userRepo.save(user)).thenReturn(null);

         assertEquals(0,userService.saveUser(user));
    }

    @Test
    public void authenticateUserTest() {

    	 User user = new User();

    	 user.setUsername("user");
    	 user.setEmail("user@gmail.com");
    	 user.setFirstname("user");
    	 user.setLastname("user");
    	 user.setPassword("user");
         List<User> users = new ArrayList<>();
         users.add(user);
        when(userRepo.findAll()).thenReturn(users);
        assertEquals(user, userService.authenticateUser(user));
    }
    

    @Test
    public void authenticateAdminNegativeTest() {

    	 User user = new User();

    	 user.setUsername("user");
    	 user.setEmail("user@gmail.com");
    	 user.setFirstname("user");
    	 user.setLastname("user");
    	 user.setPassword("user");
    	 List<User> users = new ArrayList<>();
         users.add(user);
         
         User user1 = new User();

    	 user1.setUsername("user1");
    	 user1.setEmail("user1@gmail.com");
    	 user1.setFirstname("user1");
    	 user1.setLastname("user1");
    	 user1.setPassword("user1");
        when(userRepo.findAll()).thenReturn(users);
        assertEquals(null,userService.authenticateUser(user1));
    }

    @Test
    public void findUserTest()
    {
    	User user = new User();

    	user.setUsername("user");
    	user.setEmail("user@gmail.com");
    	user.setFirstname("user");
    	user.setLastname("user");
    	user.setPassword("user");
        List<User> users = new ArrayList<>();
        users.add(user);
       when(userRepo.findAll()).thenReturn(users);
       assertEquals(user, userService.findUser(user.getEmail()));
    }
    
    @Test
    public void findUserNegativeTest()
    {
    	User user = new User();

    	user.setUsername("user");
    	user.setEmail("user@gmail.com");
    	user.setFirstname("user");
    	user.setLastname("user");
    	user.setPassword("user");
        List<User> users = new ArrayList<>();
        users.add(user);
       when(userRepo.findAll()).thenReturn(users);
       assertEquals(null, userService.findUser("tt@gmail.com"));
    }
    
   
    
    @Test
    public void getAllOrdersTest() {

        Order order = new Order();
        
        order.setName("storename");
        order.setEmail("email@gmail.com");
        
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        
        when(orderRepo.findAll()).thenReturn(orders);

        assertEquals(orders, userService.getAllOrders());

    }
    
  
    
    
    
    

}