package se.swt6.marketplace.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class RetailerArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "retailerId")
    private Retailer retailer;

    @ManyToOne
    @JoinColumn(name = "articleId")
    private Article article;

    // Getters and Setters
}

