package se.swt6.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.swt6.marketplace.entity.ArticleCart;

public interface ArticleCartRepository extends JpaRepository<ArticleCart, Integer> {
}

