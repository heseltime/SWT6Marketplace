package se.swt6.marketplace.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.swt6.marketplace.entity.Article;
import se.swt6.marketplace.service.ArticleManagement;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/articles")
@CrossOrigin(origins = "http://localhost:3000")
public class ArticleController {

    @Autowired
    private ArticleManagement articleService;

    @Operation(summary = "Get all articles", description = "Retrieve a list of all articles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Article.class)))
    })
    @GetMapping
    public List<Article> getAllArticles() {
        return articleService.getAllArticles();
    }

    @Operation(summary = "Get article by ID", description = "Retrieve an article by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved article",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Article.class))),
            @ApiResponse(responseCode = "404", description = "Article not found",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public Article getArticleById(@PathVariable Integer id) {
        return articleService.getArticleById(id);
    }

    @Operation(summary = "Create a new article", description = "Create a new article")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created article",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Article.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    @PostMapping
    public Article createArticle(@RequestBody Article article) {
        return articleService.createArticle(article);
    }

    @Operation(summary = "Update an article", description = "Update an existing article")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated article",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Article.class))),
            @ApiResponse(responseCode = "404", description = "Article not found",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public Article updateArticle(@PathVariable Integer id, @RequestBody Article article) {
        article.setArticleId(id);
        return articleService.updateArticle(article);
    }

    @Operation(summary = "Delete an article", description = "Delete an article by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted article",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Article not found",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable Integer id) {
        articleService.deleteArticle(id);
    }

    @Operation(summary = "Search articles by name", description = "Search articles by their name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved articles",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Article.class)))
    })
    @GetMapping("/search/byName")
    public List<Article> searchArticlesByName(@RequestParam String name) {
        return articleService.searchArticlesByName(name);
    }

    @Operation(summary = "Search articles by description", description = "Search articles by their description")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved articles",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Article.class)))
    })
    @GetMapping("/search/byDescription")
    public List<Article> searchArticlesByDescription(@RequestParam String description) {
        return articleService.searchArticlesByDescription(description);
    }

    @Operation(summary = "Search articles by price range", description = "Search articles within a specific price range")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved articles",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Article.class)))
    })
    @GetMapping("/search/byPriceRange")
    public List<Article> searchArticlesByPriceRange(@RequestParam BigDecimal minPrice, @RequestParam BigDecimal maxPrice) {
        return articleService.searchArticlesByPriceRange(minPrice, maxPrice);
    }
}
