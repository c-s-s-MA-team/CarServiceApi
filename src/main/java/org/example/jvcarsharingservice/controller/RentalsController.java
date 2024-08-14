package org.example.jvcarsharingservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.jvcarsharingservice.dto.rental.CreateRentalRequestDto;
import org.example.jvcarsharingservice.dto.rental.RentalDto;
import org.example.jvcarsharingservice.servece.rental.RentalService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rentals")
@RequiredArgsConstructor
public class RentalsController {
    private final RentalService rentalService;

    @Operation(summary = "Add a new rental")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RentalDto addRental(
            @RequestBody @Valid CreateRentalRequestDto createRentalRequestDto) {
        return rentalService.addRental(createRentalRequestDto);
    }

    @Operation(summary = "Get rentals by user ID and whether the rental is active")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RentalDto> getRentals(@RequestParam Long userId, @RequestParam boolean isActive) {
        return rentalService.getRentals(userId, isActive);
    }

    @Operation(summary = "Get specific rental")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RentalDto getRental(@PathVariable Long id) {
        return rentalService.getRental(id);
    }

    @Operation(summary = "Return a rental")
    @PostMapping("/{id}/return")
    @ResponseStatus(HttpStatus.OK)
    public RentalDto returnRental(@PathVariable Long id) {
        return rentalService.returnRental(id);
    }
}
