package org.example.jvcarsharingservice.servece.rental;

import jakarta.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.jvcarsharingservice.dto.rental.CreateRentalRequestDto;
import org.example.jvcarsharingservice.dto.rental.RentalDto;
import org.example.jvcarsharingservice.dto.rental.RentalSearchParameters;
import org.example.jvcarsharingservice.mapper.RentalMapper;
import org.example.jvcarsharingservice.model.classes.Car;
import org.example.jvcarsharingservice.model.classes.Rental;
import org.example.jvcarsharingservice.repository.car.CarRepository;
import org.example.jvcarsharingservice.repository.rental.RentalRepository;
import org.example.jvcarsharingservice.repository.rental.provider.RentalSpecificationBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements RentalService {
    private final RentalRepository rentalRepository;
    private final RentalMapper rentalMapper;
    private final CarRepository carRepository;
    private final RentalSpecificationBuilder rentalSpecificationBuilder;
    
    @Override
    public RentalDto addRental(
            CreateRentalRequestDto createRentalRequestDto) {
        updateCarInventoryAfterRent(createRentalRequestDto);
        return rentalMapper.toDto(
                rentalRepository.save(
                        rentalMapper.toEntity(createRentalRequestDto))
        );
    }

    private void updateCarInventoryAfterRent(CreateRentalRequestDto createRentalRequestDto) {
        Car car = carRepository.findById(createRentalRequestDto.carId()).orElseThrow(
                () -> new EntityNotFoundException(
                        "Car with id " + createRentalRequestDto.carId() + " not found")
        );
        car.setInventory(car.getInventory() - 1);
    }

    @Override
    public List<RentalDto> getRentals(RentalSearchParameters rentalSearchParameters) {
        Specification<Rental> build = rentalSpecificationBuilder.build(rentalSearchParameters);
        List<Rental> all = rentalRepository.findAll(build);
        List<RentalDto> list = all
                .stream()
                .map(rentalMapper::toDto)
                .toList();
        return list;
    }

    @Override
    public RentalDto getRental(Long id) {
        return rentalMapper.toDto(
                rentalRepository.findById(id).orElseThrow(
                        () -> new EntityNotFoundException("Rental with id " + id + " not found")
                )
        );
    }

    @Override
    public void returnRental(Long id) {
        updateCarInventoryAfterReturnRent(id);
        rentalRepository.deleteById(id);
    }

    private void updateCarInventoryAfterReturnRent(Long id) {
        Rental rental = rentalRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Rental with id " + id + " not found")
        );
        rental.setActualReturnDate(LocalDate.now());
        rental.setId(id);
        Long carId = rental.getCarId();
        Car car = carRepository.findById(carId).orElseThrow(
                () -> new EntityNotFoundException("Car with id " + carId + " not found")
        );
        car.setInventory(car.getInventory() + 1);

        rentalRepository.save(rental);
    }
}
