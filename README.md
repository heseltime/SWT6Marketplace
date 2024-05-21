### Project Documentation

#### Structure of the Project (as Suggested)

```
+------------------+    +-------------------------------------------------------+
|    Frontend      |    |                         Backend                       |
|                  |    |                   |               |                   |
|  +--------+      |    | PresentationLayer | ServiceLayer  | PersistenceLayer  |
|  | WebApp |      |    |                   |               |                   |
|  | Model  |      |    |                   |               |                   |
|  | HTML   | -----+--> | Controller    DTO |   Service     | DAO/Repo  Entity  |
|  +--------+      |    |                   |  Components   |                   |
|                  |    |                   |               |                   |
|                  |    |                                                       |
+------------------+    +-------------------------------------------------------+
```

In the following the focus is on the backend, the **marketplace** app. At the end screenshots of the **marketplace-frontend** are also demonstrated to show how it comes together. The app was developed pretty rapidly, showcasing Spring Boot's advantages.

#### Project Setup 

![Via Initializr](img/Screenshot%202024-05-20%20222936.png)

#### Database Ininitalization

H2 In-memory Java database: file-based storage to change the behavior from volativel data loss after app restart. See data.sql in src/main/resources to do initialization. (Connection string `jdbc:h2:mem:default`.)

#### Database Schema and Dummy Content

The schema from Ue2, in the dialect required by H2 and put in schema.sql in the resources folder - this was subsequently commented out to make use of Spring Data (entity-driven db generation, together with CommandLineRunner in MarketplaceApplication to add the test data).

```
-- Create schema if it does not exist
create schema if not exists MARKETPLACE;

-- Set the schema for the session
set schema MARKETPLACE;

-- Create tables if they do not already exist
create table if not exists ARTICLE
(
    ARTICLE_ID         INTEGER generated always as identity
        primary key,
    NAME               VARCHAR(255)   not null,
    SHORT_DESCRIPTION  VARCHAR(500)   not null,
    PRICE              DECIMAL(10, 2) not null,
    STOCK_AMOUNT       INTEGER,
    SHIPMENT_COUNTRIES VARCHAR(255),
    SHIPMENT_COST      DECIMAL(10, 2),
    RATING             INTEGER
);

create table if not exists CUSTOMER
(
    CUSTOMER_ID      INTEGER generated always as identity
        primary key,
    NAME             VARCHAR(255) not null,
    BILLING_ADDRESS  VARCHAR(255) not null,
    EMAIL            VARCHAR(255) not null,
    SHIPMENT_ADDRESS VARCHAR(255)
);

create table if not exists CART
(
    CART_ID     INTEGER generated always as identity
        primary key,
    CUSTOMER_ID INTEGER
        constraint FK_CART_CUSTOMER_ID
        references CUSTOMER
);

create table if not exists ARTICLE_CART
(
    ID         INTEGER generated always as identity
        primary key,
    ARTICLE_ID INTEGER
        constraint FK_ARTICLE_ID
        references ARTICLE,
    CART_ID    INTEGER
        constraint FK_CART_ID
        references CART,
    AMOUNT     INTEGER
);

create table if not exists PAYMENTMETHOD
(
    PM_ID           INTEGER generated always as identity
        primary key,
    PROVIDER        VARCHAR(50),
    USER_IDENTIFIER VARCHAR(50),
    CARD_NUMBER     VARCHAR(16),
    IBAN            VARCHAR(34),
    CHECK_DIGIT     VARCHAR(5),
    CUSTOMER_ID     INTEGER
        constraint FK_CUSTOMER_ID
        references CUSTOMER
);

create table if not exists RETAILER
(
    RETAILER_ID INTEGER generated always as identity
        primary key,
    NAME        VARCHAR(255) not null,
    ADDRESS     VARCHAR(255) not null
);

create table if not exists DISCOUNTCODE
(
    CODE_ID     INTEGER generated always as identity
        primary key,
    RETAILER_ID INTEGER
        constraint FK_DISCOUNTCODE_RETAILER_ID
        references RETAILER,
    CODE        VARCHAR(20),
    VALID_FROM  DATE,
    VALID_TO    DATE
);

create table if not exists RETAILER_ARTICLE
(
    ID          INTEGER generated always as identity
        primary key,
    RETAILER_ID INTEGER
        constraint FK_RETAILER_ID
        references RETAILER,
    ARTICLE_ID  INTEGER
        constraint FK_ARTICLE_RETAILER_ID
        references ARTICLE
);


-- Add dummy content for all tables ...
-- Insert dummy data into ARTICLE
insert into ARTICLE (NAME, SHORT_DESCRIPTION, PRICE, STOCK_AMOUNT, SHIPMENT_COUNTRIES, SHIPMENT_COST, RATING) values
    ('Article 1', 'Short description for article 1', 19.99, 100, 'USA, Canada', 5.00, 4),
    ('Article 2', 'Short description for article 2', 29.99, 200, 'USA, UK', 7.50, 5),
    ('Article 3', 'Short description for article 3', 39.99, 150, 'Canada, UK', 6.00, 3);

-- Insert dummy data into CUSTOMER
insert into CUSTOMER (NAME, BILLING_ADDRESS, EMAIL, SHIPMENT_ADDRESS) values
    ('John Doe', '123 Main St, Anytown, USA', 'johndoe@example.com', '123 Main St, Anytown, USA'),
    ('Jane Smith', '456 Elm St, Othertown, USA', 'janesmith@example.com', '456 Elm St, Othertown, USA'),
    ('Alice Johnson', '789 Oak St, Anycity, USA', 'alicejohnson@example.com', '789 Oak St, Anycity, USA');

-- Insert dummy data into CART
insert into CART (CUSTOMER_ID) values
   (1),
   (2),
   (3);

-- Insert dummy data into ARTICLE_CART
insert into ARTICLE_CART (ARTICLE_ID, CART_ID, AMOUNT) values
   (1, 1, 2),
   (2, 1, 1),
   (3, 2, 1),
   (1, 3, 3);

-- Insert dummy data into PAYMENTMETHOD
insert into PAYMENTMETHOD (PROVIDER, USER_IDENTIFIER, CARD_NUMBER, IBAN, CHECK_DIGIT, CUSTOMER_ID) values
   ('Visa', 'john_doe', '4111111111111111', 'DE89370400440532013000', '12345', 1),
   ('MasterCard', 'jane_smith', '5500000000000004', 'DE89370400440532013001', '67890', 2),
   ('PayPal', 'alice_johnson', null, 'DE89370400440532013002', null, 3);

-- Insert dummy data into RETAILER
insert into RETAILER (NAME, ADDRESS) values
    ('Retailer 1', '101 Market St, Somecity, USA'),
    ('Retailer 2', '202 Commerce St, Anothertown, USA');

-- Insert dummy data into DISCOUNTCODE
insert into DISCOUNTCODE (RETAILER_ID, CODE, VALID_FROM, VALID_TO) values
   (1, 'DISCOUNT10', '2024-01-01', '2024-12-31'),
   (2, 'SAVE20', '2024-01-01', '2024-12-31');

-- Insert dummy data into RETAILER_ARTICLE
insert into RETAILER_ARTICLE (RETAILER_ID, ARTICLE_ID) values
    (1, 1),
    (1, 2),
    (2, 3);

```

The Swagger Schema view:

![Swagger Schema View](img/Screenshot%202024-05-21%20101948.png)

#### DB Config

JDBC URL: `jdbc:h2:mem:testdb`

![H2 Console](img/Screenshot%202024-05-20%20225143.png)

#### OpenAPI Description/Swagger UI

JSON-format descriotion at default URL: http://localhost:8080/v3/api-docs

Swagger UI: http://localhost:8080/swagger-ui/index.html

**Before Annotation**:

![Swagger UI representation of the Controller endpoints](img/Screenshot%202024-05-21%20003320.png)

**After Annotation** in the source code:

![Swagger UI representation of the Controller endpoints with annotations](img/Screenshot%202024-05-21%20102000.png)

#### Services and Controllers

ArticleService/Controller (ArticleManagement is the service name) and same for Customer (CustomerManagement) were added to the project, the extra task that would require a CartService/Controller combination was not attempted.

For Articles, the search functionality was prepared as follows:

```
// In the Controller ...
// Search endpoints
@GetMapping("/search/byName")
public List<Article> searchArticlesByName(@RequestParam String name) {
    return articleService.searchArticlesByName(name);
}

@GetMapping("/search/byDescription")
public List<Article> searchArticlesByDescription(@RequestParam String description) {
    return articleService.searchArticlesByDescription(description);
}

@GetMapping("/search/byPriceRange")
public List<Article> searchArticlesByPriceRange(@RequestParam BigDecimal minPrice, @RequestParam BigDecimal maxPrice) {
    return articleService.searchArticlesByPriceRange(minPrice, maxPrice);
}

// In the Service
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

// ... and the Repository accordingly:
@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    List<Article> findByNameContainingIgnoreCase(String name);
    List<Article> findByShortDescriptionContainingIgnoreCase(String description);
    List<Article> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
}
```

#### AuthController (Mock) and ShippingController

To play around with further controllers, these were added as well. The authentication mocks a real authentication process.

While the AuthController refers to the customer entity, the ShippingController makes use of a ShippingRequest DTO (Data Transfer Object) - to reflect on the difference:

- **Entity**: Represents a database table. Contains business logic and is managed by an ORM (Object-Relational Mapping) framework.
- **DTO**: Used for transferring data between layers. Contains no business logic or persistence annotations.

#### Frontend (marketplace-frontend)

This was implemented in React.

#### Result (Screenshots)

This was mainly a Spring Boot API project, so I would actually say the annotated Swagger OpenAPI document is the main output of this exercise. That said, here are the screenshots of a lightweight frontend:

![Frontend 1](img/Screenshot%202024-05-21%20101758.png)
![Frontend 2](img/Screenshot%202024-05-21%20101809.png)

**Update**: almost forgot search functionality.

![Frontend 2a](img/Screenshot%202024-05-21%20104257.png)
![Frontend 2b](img/Screenshot%202024-05-21%20104312.png)

![Frontend 3](img/Screenshot%202024-05-21%20101823.png)
![Frontend 4](img/Screenshot%202024-05-21%20101848.png)

#### Provisioning as "fat jar"

This was also **uploaded to E-learning, zipped together with the React build** (`npm run build`, in target and build subfolders respectively). **Source code here in GitHub** (Classroom). Command to run `java -jar target/your-spring-boot-app.jar` after building with `mvn clean package`.

![Fat Jar Runs](img/Screenshot%202024-05-21%20103135.png)

Interesting to me: the notion of provisioning the React app directly with the Java project.

To run from development though: `npm start` in frontend folder and best to manage maven lifecycle from IntelliJ for the main project.

#### References

I made use of this video tutorial, which I can recommend: https://www.youtube.com/watch?v=UgX5lgv4uVM (May 20, 2024).