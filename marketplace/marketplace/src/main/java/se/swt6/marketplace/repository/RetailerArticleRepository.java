package se.swt6.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.swt6.marketplace.entity.RetailerArticle;

public interface RetailerArticleRepository extends JpaRepository<RetailerArticle, Integer> {
}
