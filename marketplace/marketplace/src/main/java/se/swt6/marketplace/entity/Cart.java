package se.swt6.marketplace.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartId;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    // Getters and Setters
}