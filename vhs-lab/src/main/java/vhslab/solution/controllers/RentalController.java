package vhslab.solution.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vhslab.solution.entities.dto.RentalEntityDto;
import vhslab.solution.service.RentalEntityService;

import java.math.BigDecimal;
import java.sql.Date;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {
    @Autowired
    private RentalEntityService rentalService;

    @PostMapping("/rent")
    public ResponseEntity<RentalEntityDto> rentVhs(
            @RequestParam long userId,
            @RequestParam long vhsId,
            @RequestParam Date dateRented) {
        try {
            RentalEntityDto rental = rentalService.rentVhs(userId, vhsId, dateRented);
            return new ResponseEntity<>(rental, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/{rentalId}/return")
    public ResponseEntity<RentalEntityDto> returnRentedVhs(@PathVariable long rentalId) {
        try {
            RentalEntityDto lateReturnFee = rentalService.returnRentedVhs(rentalId);
            return new ResponseEntity<>(lateReturnFee, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}