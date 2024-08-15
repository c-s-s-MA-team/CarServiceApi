package org.example.jvcarsharingservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.jvcarsharingservice.dto.rental.CreateRentalRequestDto;
import org.example.jvcarsharingservice.dto.rental.RentalDto;
import org.example.jvcarsharingservice.dto.rental.RentalSearchParameters;
import org.example.jvcarsharingservice.servece.rental.RentalService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
    public List<RentalDto> getRentals(RentalSearchParameters parameters) {
        return rentalService.getRentals(parameters);
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
    public void returnRental(@PathVariable Long id) {
        rentalService.returnRental(id);
    }
}
