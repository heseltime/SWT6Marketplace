package se.swt6.marketplace.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ArticleCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "articleId")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "cartId")
    private Cart cart;

    private Integer amount;

    // Getters and Setters
}
