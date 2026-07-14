package com.communityhub.userservice.application;

import com.communityhub.userservice.domain.User;
import com.communityhub.userservice.infrastructure.UserRepository;
import com.communityhub.userservice.presentation.dto.UserRegistrationDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    void registerUser_ShouldThrowException_WhenEmailAlreadyExists() {

        UserRegistrationDTO dto = new UserRegistrationDTO("Lucas", "lucas@email.com", "123456");

        User checkUser = new User();

        checkUser.setEmail("lucas@email.com");

        when(userRepository.findByEmail("lucas@email.com")).thenReturn(Optional.of(checkUser));

        assertThrows(RuntimeException.class, () -> userService.registerUser(dto));

        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void registerUser_ShouldSaveUser_WhenEmailIsNew() {

        UserRegistrationDTO dto = new UserRegistrationDTO("Lucas", "novo@email.com", "senha123");

        when(userRepository.findByEmail(dto.email())).thenReturn(Optional.empty());

        when(passwordEncoder.encode(dto.password())).thenReturn("hashSeguro123");

        userService.registerUser(dto);

        verify(userRepository, times(1)).save(any(User.class));
    }

}
