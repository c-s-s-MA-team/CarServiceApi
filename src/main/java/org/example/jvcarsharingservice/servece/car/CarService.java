package org.example.jvcarsharingservice.servece.car;

import java.util.List;
import org.example.jvcarsharingservice.dto.car.CarDetailsDto;
import org.example.jvcarsharingservice.dto.car.CarDto;
import org.example.jvcarsharingservice.dto.car.CarRequestDto;
import org.springframework.data.domain.Pageable;

public interface CarService {
    CarDto addCar(CarRequestDto createCarRequestDto);

    List<CarDto> getCars(Pageable pageable);

    CarDetailsDto getCarDetails(Long id);

    CarDto updateCar(Long id, CarRequestDto updateCarRequestDto);

    void delete(Long id);
}
