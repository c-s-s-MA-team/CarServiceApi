package org.example.jvcarsharingservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @Operation(summary = "Register a new user")
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Object register() {
        // Implement registration logic
        return null;
    }

    @Operation(summary = "User login to obtain JWT tokens")
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public Object login() {
        // Implement login logic
        return null;
    }
}

