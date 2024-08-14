package org.example.jvcarsharingservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.jvcarsharingservice.dto.user.UpdateUserRequestDto;
import org.example.jvcarsharingservice.dto.user.UserDto;
import org.example.jvcarsharingservice.servece.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {
    private final UserService userService;

    @Operation(summary = "Update user role",
            description = """
                    If user have Role = Manager then Manager => Customer,\n 
                    If user have Role = Customer then Customer => Manager, 
                    """)
    @PutMapping("/{id}/role")
    @ResponseStatus(HttpStatus.OK)
    public UserDto updateUserRole(@PathVariable Long id) {
        return userService.updateRole(id);
    }

    @Operation(summary = "Get my profile info")
    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getMyProfile() {
        String email = null;
        return userService.getMyProfile(email);
    }

    @Operation(summary = "Update my profile info")
    @PutMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    public UserDto updateMyProfile(
            @RequestBody @Valid UpdateUserRequestDto request) {
        String email = null;
        return userService.updateMyProfile(email, request);
    }
}

