package com.roriga.webbserviceandawsserver.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

import com.roriga.webbserviceandawsserver.entity.User;
import com.roriga.webbserviceandawsserver.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    void setUp() {
        // Initialiserar en användare som kan användas i testerna
        user = new User();
        user.setId(1L);
        user.setUsername("testUser");
        user.setEmail("test@example.com");
        user.setPassword("password");
    }

    @Test
    void whenRegisterNewUser_thenUserShouldBeReturned() {
        // Konfigurerar mocken att returnera en specifik användare när save() anropas
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.registerNewUser(new User());

        assertNotNull(result);
        assertEquals("testUser", result.getUsername());
    }

    @Test
    void whenUpdateUserWithExistingId_thenUpdatedUserShouldBeReturned() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        User updatedUser = new User();
        updatedUser.setUsername("updatedUsername");

        User result = userService.updateUser(user.getId(), updatedUser);

        assertNotNull(result);
        assertEquals("updatedUsername", result.getUsername());
    }

    @Test
    void whenUpdateUserWithNonExistingId_thenExceptionShouldBeThrown() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.updateUser(2L, new User());
        });

        assertEquals("Användaren hittades inte", exception.getMessage());
    }

}
