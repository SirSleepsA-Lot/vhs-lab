package vhslab.solution.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RentalController {
    public RentalController() {
    }

    @GetMapping({"/"})
    public String home() {
        return "Welcome to the home page222!";
    }
}