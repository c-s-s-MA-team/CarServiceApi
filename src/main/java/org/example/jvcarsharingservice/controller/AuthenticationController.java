package org.example.jvcarsharingservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.jvcarsharingservice.dto.user.UserDto;
import org.example.jvcarsharingservice.dto.user.login.LoginRequestDto;
import org.example.jvcarsharingservice.dto.user.registration.RegisterRequestDto;
import org.example.jvcarsharingservice.servece.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;

    @Operation(summary = "Register a new user")
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto register(
            @RequestBody @Valid RegisterRequestDto requestDto) {
        return userService.register(requestDto);
    }

    @Operation(summary = "User login to obtain JWT tokens")
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public UserDto login(
            @RequestBody @Valid LoginRequestDto requestDto) {
        return userService.login(requestDto);
    }
}

