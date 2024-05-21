package se.swt6.marketplace.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
public class DiscountCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codeId;
    private String code;
    private LocalDate validFrom;
    private LocalDate validTo;

    @ManyToOne
    @JoinColumn(name = "retailerId")
    private Retailer retailer;

    // Getters and Setters
}
