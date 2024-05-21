package se.swt6.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.swt6.marketplace.entity.Article;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    List<Article> findByNameContainingIgnoreCase(String name);
    List<Article> findByShortDescriptionContainingIgnoreCase(String description);
    List<Article> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
}

