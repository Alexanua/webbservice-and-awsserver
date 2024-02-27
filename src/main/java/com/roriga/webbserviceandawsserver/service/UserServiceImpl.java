package com.roriga.webbserviceandawsserver.service;


import com.roriga.webbserviceandawsserver.entity.User;
import com.roriga.webbserviceandawsserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User registerNewUser(User user) {
        // Implementera logik för att registrera en ny användare
        // Kontrollera till exempel att användarnamnet eller e-postadressen inte redan finns
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long userId, User user) {
        // Implementera logik för att uppdatera en befintlig användare
        // Kontrollera att användaren finns innan uppdatering
        return userRepository.findById(userId)
                .map(existingUser -> {
                    existingUser.setUsername(user.getUsername());
                    existingUser.setEmail(user.getEmail());
                    // Uppdatera ytterligare fält efter behov
                    return userRepository.save(existingUser);
                }).orElseThrow(() -> new RuntimeException("Användaren hittades inte"));
    }

    @Override
    public void deleteUser(Long userId) {
        // Implementera logik för att radera en användare
        userRepository.deleteById(userId);
    }

    @Override
    public User findUserById(Long userId) {
        // Hämta en användare med specifikt ID
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Användaren hittades inte"));
    }

    @Override
    public List<User> findAllUsers() {
        // Returnera en lista av alla användare
        return userRepository.findAll();
    }
}
