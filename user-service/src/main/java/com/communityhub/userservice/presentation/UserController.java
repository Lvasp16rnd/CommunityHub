package com.communityhub.userservice.presentation;

import com.communityhub.userservice.application.UserService;
import com.communityhub.userservice.presentation.dto.UserRegistrationDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistrationDTO dto) {
        userService.registerUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário recebido com sucesso");

    }

}
