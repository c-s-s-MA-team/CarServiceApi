package org.example.jvcarsharingservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.access.prepost.PreAuthorize;

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

