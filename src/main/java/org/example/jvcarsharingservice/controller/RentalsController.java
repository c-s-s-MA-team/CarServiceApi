package org.example.jvcarsharingservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rentals")
@RequiredArgsConstructor
public class RentalsController {

    @Operation(summary = "Add a new rental")
    @PostMapping
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.CREATED)
    public Object addRental() {
        // Implement add rental logic
        return null;
    }

    @Operation(summary = "Get rentals by user ID and whether the rental is active")
    @GetMapping
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.OK)
    public Object getRentals(@RequestParam Long userId, @RequestParam boolean isActive) {
        // Implement get rentals logic
        return null;
    }

    @Operation(summary = "Get specific rental")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Object getRental(@PathVariable Long id) {
        // Implement get rental logic
        return null;
    }

    @Operation(summary = "Return a rental")
    @PostMapping("/{id}/return")
    @PreAuthorize("isAuthenticated()")
    @ResponseStatus(HttpStatus.OK)
    public Object returnRental(@PathVariable Long id) {
        // Implement return rental logic
        return null;
    }
}
