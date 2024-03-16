package vhslab.solution.entities;
import jakarta.persistence.*;
@Entity
public class VHS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String director;
    private int year;

    // Getters and setters
    // Constructors
}