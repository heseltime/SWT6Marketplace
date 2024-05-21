package se.swt6.marketplace.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pmId;
    private String provider;
    private String userIdentifier;
    private String cardNumber;
    private String iban;
    private String checkDigit;

    @ManyToOne
    @JoinColumn(name = "customerId")
    private Customer customer;

    // Getters and Setters
}
