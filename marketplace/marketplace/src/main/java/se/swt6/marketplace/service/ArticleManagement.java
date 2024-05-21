package se.swt6.marketplace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.swt6.marketplace.entity.Article;
import se.swt6.marketplace.repository.ArticleRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ArticleManagement {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public Article getArticleById(Integer id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }

    public Article updateArticle(Article article) {
        return articleRepository.save(article);
    }

    public void deleteArticle(Integer id) {
        articleRepository.deleteById(id);
    }

    // Search methods
    public List<Article> searchArticlesByName(String name) {
        return articleRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Article> searchArticlesByDescription(String description) {
        return articleRepository.findByShortDescriptionContainingIgnoreCase(description);
    }

    public List<Article> searchArticlesByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return articleRepository.findByPriceBetween(minPrice, maxPrice);
    }
}
