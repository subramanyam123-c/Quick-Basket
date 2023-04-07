package com.example.service.impl;

import com.example.dao.UserDAO;
import com.example.dao.Impl.UserDAOImpl;
import com.example.model.User;
import com.example.service.UserService;
import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO;
    
    public UserServiceImpl() throws SQLException {
        userDAO = new UserDAOImpl();
    }
    
    @Override
    public boolean login(String email, String password) {
        User user = userDAO.getUser(email);
        if (user != null && user.getPassword().equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void register(User user) {
        userDAO.addUser(user);
    }
}
