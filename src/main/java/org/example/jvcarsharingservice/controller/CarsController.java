package org.example.jvcarsharingservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.jvcarsharingservice.dto.car.CarDetailsDto;
import org.example.jvcarsharingservice.dto.car.CarDto;
import org.example.jvcarsharingservice.dto.car.CarRequestDto;
import org.example.jvcarsharingservice.servece.car.CarService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarsController {
    private final CarService carService;

    @Operation(summary = "Add a new car",
            description = "types = SEDAN, SUV, HATCHBACK, UNIVERSAL")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarDto addCar(@RequestBody @Valid CarRequestDto createCarRequestDto) {
        return carService.addCar(createCarRequestDto);

    }

    @Operation(summary = "Get a list of cars")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CarDto> getCars(Pageable pageable) {
        return carService.getCars(pageable);
    }

    @Operation(summary = "Get car's detailed information")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CarDetailsDto getCarDetails(@PathVariable Long id) {
        return carService.getCarDetails(id);
    }

    @Operation(summary = "Update car information",
            description = "types = SEDAN, SUV, HATCHBACK, UNIVERSAL")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CarDto updateCar(@PathVariable Long id,
                            @RequestBody @Valid CarRequestDto updateCarRequestDto) {
        return carService.updateCar(id, updateCarRequestDto);
    }

    @Operation(summary = "Delete car")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCar(@PathVariable Long id) {
        carService.delete(id);
    }
}
