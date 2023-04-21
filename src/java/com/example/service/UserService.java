package com.example.service;

import com.example.model.User;

public interface UserService {

    public User login(String email, String password);

    public void register(User user);
}
