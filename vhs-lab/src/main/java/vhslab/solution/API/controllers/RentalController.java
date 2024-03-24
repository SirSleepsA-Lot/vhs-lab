package vhslab.solution.API.controllers;


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vhslab.solution.API.Validators.Annotations.FutureOrPresentForDate;
import vhslab.solution.API.Validators.Annotations.PastOrPresentForDate;
import vhslab.solution.entities.dto.RentalEntityDto;
import vhslab.solution.service.RentalEntityService;


import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {
    @Autowired
    private RentalEntityService rentalService;

    @GetMapping("/{id}")
    public ResponseEntity<RentalEntityDto> getRentalById(@PathVariable(value = "id") @NotNull long rentalId) throws Exception {
        return ResponseEntity.ok(rentalService.getRentalById(rentalId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RentalEntityDto> updateRental(@PathVariable(value = "id") @NotNull long rentalId,
                                                        @Valid @RequestBody RentalEntityDto rentalDetails) {
        RentalEntityDto updatedRental = rentalService.updateRental(rentalId, rentalDetails);
        return ResponseEntity.ok(updatedRental);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRental(@PathVariable(value = "id") @NotNull long rentalId) {
        rentalService.deleteRental(rentalId);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/rent/{userId}/{vhsId}/{dateRented}/{dateDue}")
    public ResponseEntity<RentalEntityDto> rentVhs(
            @PathVariable @NotNull long userId,
            @PathVariable @NotNull  long vhsId,
            @PathVariable @PastOrPresentForDate Date dateRented,
            @PathVariable @FutureOrPresentForDate Date dateDue) throws Exception {

            RentalEntityDto rental = rentalService.rentVhs(userId, vhsId, dateRented, dateDue);
            return new ResponseEntity<>(rental, HttpStatus.CREATED);

    }
    @GetMapping("/list")
    public ResponseEntity<List<RentalEntityDto>> getAllRentalEntities() {
        List<RentalEntityDto> rentalEntities = rentalService.getAllRentalEntities();
        return ResponseEntity.ok(rentalEntities);
    }
    @GetMapping("/return/{rentalId}")
    public ResponseEntity<RentalEntityDto> returnRentedVhs(@PathVariable @NotNull long rentalId) throws Exception {
            RentalEntityDto lateReturnFee = rentalService.returnRentedVhs(rentalId);
            return new ResponseEntity<>(lateReturnFee, HttpStatus.OK);

    }
    @GetMapping("/pay/{rentalId}")
    public ResponseEntity<Boolean> payLateFee(@PathVariable @NotNull long rentalId) throws Exception {
        Boolean lateReturnFee = rentalService.payFee(rentalId);
        return new ResponseEntity<>(lateReturnFee, HttpStatus.OK);

    }

}