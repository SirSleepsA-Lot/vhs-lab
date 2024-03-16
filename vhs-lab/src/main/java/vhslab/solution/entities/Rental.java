package vhslab.solution.entities;

import jakarta.persistence.*;

@Entity
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "id") // Name of the foreign key column in the database
    private User user;

    // Getters and setters
    // Constructors
}
