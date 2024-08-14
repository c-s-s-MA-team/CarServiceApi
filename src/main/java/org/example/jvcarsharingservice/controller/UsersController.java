package org.example.jvcarsharingservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    @Operation(summary = "Update user role")
    @PutMapping("/{id}/role")
    @PreAuthorize("hasRole('MANAGER')")
    @ResponseStatus(HttpStatus.OK)
    public Object updateUserRole(@PathVariable Long id) {
        // Implement role update logic
        return null;
    }

    @Operation(summary = "Get my profile info")
    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.OK)
    public Object getMyProfile() {
        // Implement get profile logic
        return null;
    }

    @Operation(summary = "Update my profile info")
    @PutMapping("/me")
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.OK)
    public Object updateMyProfile() {
        // Implement profile update logic
        return null;
    }
}

