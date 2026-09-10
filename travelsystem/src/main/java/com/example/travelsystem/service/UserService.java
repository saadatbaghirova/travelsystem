package com.example.travelsystem.service;

import com.example.travelsystem.model.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    List<User> getAllUsers();

    User findUserById(Long id);

    User updateUser(User user);

    void deleteUserById(Long id);
}