package se.swt6.marketplace.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;

// This leads to an Article table (outside of schema in resources/schema.sql)
@Entity
@Data
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer articleId;
    private String name;
    private String shortDescription;
    private BigDecimal price;
    private Integer stockAmount;
    private String shipmentCountries;
    private BigDecimal shipmentCost;
    private Integer rating;

    // Getters and Setters
}