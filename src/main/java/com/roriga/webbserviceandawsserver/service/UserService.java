package com.roriga.webbserviceandawsserver.service;

import com.roriga.webbserviceandawsserver.entity.User;

import java.util.List;

public interface UserService {
    User registerNewUser(User user);
    User updateUser(Long userId, User user);
    void deleteUser(Long userId);
    User findUserById(Long userId);
    List<User> findAllUsers();
}
