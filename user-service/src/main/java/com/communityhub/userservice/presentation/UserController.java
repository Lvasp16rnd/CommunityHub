package com.communityhub.userservice.presentation;

import com.communityhub.userservice.application.UserService;
import com.communityhub.userservice.presentation.dto.UserLoginDTO;
import com.communityhub.userservice.presentation.dto.UserRegistrationDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import com.communityhub.userservice.config.security.TokenService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public UserController(TokenService tokenService, AuthenticationManager authenticationManager,
            UserService userService) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRegistrationDTO dto) {
        userService.registerUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário recebido com sucesso");

    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody UserLoginDTO dto) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        String emailAuthenticated = auth.getName();

        String token = tokenService.generateToken(emailAuthenticated);

        return ResponseEntity.ok(token);
    }

}
