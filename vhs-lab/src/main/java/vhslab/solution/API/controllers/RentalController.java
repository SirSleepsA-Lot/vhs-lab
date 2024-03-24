package vhslab.solution.API.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vhslab.solution.entities.dto.RentalEntityDto;
import vhslab.solution.service.RentalEntityService;

import java.sql.Date;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {
    @Autowired
    private RentalEntityService rentalService;

    @GetMapping("/rent")
    public ResponseEntity<RentalEntityDto> rentVhs(
            @RequestParam long userId,
            @RequestParam long vhsId,
            @RequestParam Date dateRented) throws Exception {

            RentalEntityDto rental = rentalService.rentVhs(userId, vhsId, dateRented);
            return new ResponseEntity<>(rental, HttpStatus.CREATED);

    }
    @GetMapping("/{rentalId}/return")
    public ResponseEntity<RentalEntityDto> returnRentedVhs(@PathVariable long rentalId) throws Exception {
            RentalEntityDto lateReturnFee = rentalService.returnRentedVhs(rentalId);
            return new ResponseEntity<>(lateReturnFee, HttpStatus.OK);

    }
    @GetMapping("/{rentalId}/pay")
    public ResponseEntity<Boolean> payLateFee(@PathVariable long rentalId) throws Exception {
        Boolean lateReturnFee = rentalService.payFee(rentalId);
        return new ResponseEntity<>(lateReturnFee, HttpStatus.OK);

    }
    @GetMapping("/")
    public ResponseEntity<String> test() throws Exception {

        String rental = "yoyoyoy";
        throw new Exception(rental);
        //return new ResponseEntity<>(rental, HttpStatus.CREATED);

    }
}