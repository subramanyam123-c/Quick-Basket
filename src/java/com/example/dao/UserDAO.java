package com.example.dao;

import com.example.model.User;

public interface UserDAO {
    public User getUser(String email);
    public void addUser(User user);
}
