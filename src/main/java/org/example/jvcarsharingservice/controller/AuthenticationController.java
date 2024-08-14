package org.example.jvcarsharingservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

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

