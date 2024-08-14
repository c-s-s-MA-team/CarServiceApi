package org.example.jvcarsharingservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarsController {

    @Operation(summary = "Add a new car")
    @PostMapping
    @PreAuthorize("hasRole('MANAGER')")
    @ResponseStatus(HttpStatus.CREATED)
    public Object addCar() {
        // Implement add car logic
        return null;
    }

    @Operation(summary = "Get a list of cars")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Object getCars() {
        // Implement get cars logic
        return null;
    }

    @Operation(summary = "Get car's detailed information")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Object getCarDetails(@PathVariable Long id) {
        // Implement get car details logic
        return null;
    }

    @Operation(summary = "Update car information")
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    @ResponseStatus(HttpStatus.OK)
    public Object updateCar(@PathVariable Long id) {
        // Implement update car logic
        return null;
    }

    @Operation(summary = "Delete car")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('MANAGER')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Object deleteCar(@PathVariable Long id) {
        // Implement delete car logic
        return null;
    }
}
