package com.communityhub.userservice.application;

import com.communityhub.userservice.infrastructure.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.communityhub.userservice.presentation.dto.UserRegistrationDTO;
import com.communityhub.userservice.domain.User;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(UserRegistrationDTO dto) {
        Optional<User> checkUser = userRepository.findByEmail(dto.email());
        if (checkUser.isPresent()) {
            throw new RuntimeException("Email already exists");
        } else {
            User user = new User();
            user.setEmail(dto.email());
            user.setUsername(dto.username());
            user.setPassword(passwordEncoder.encode(dto.password()));
            userRepository.save(user);
        }

    }
}
