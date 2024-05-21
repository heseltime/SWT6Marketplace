package se.swt6.marketplace;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import se.swt6.marketplace.entity.*;
import se.swt6.marketplace.repository.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

@SpringBootApplication
public class MarketplaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketplaceApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(
			ArticleRepository articleRepository,
			CustomerRepository customerRepository,
			CartRepository cartRepository,
			ArticleCartRepository articleCartRepository,
			PaymentMethodRepository paymentMethodRepository,
			RetailerRepository retailerRepository,
			DiscountCodeRepository discountCodeRepository,
			RetailerArticleRepository retailerArticleRepository) {
		return args -> {
			// Adding dummy articles
			Article article1 = new Article();
			article1.setName("Laptop");
			article1.setShortDescription("A powerful laptop");
			article1.setPrice(new BigDecimal("999.99"));
			article1.setStockAmount(10);
			article1.setShipmentCountries("USA,Canada");
			article1.setShipmentCost(new BigDecimal("20.00"));
			article1.setRating(5);

			Article article2 = new Article();
			article2.setName("Smartphone");
			article2.setShortDescription("A latest model smartphone");
			article2.setPrice(new BigDecimal("699.99"));
			article2.setStockAmount(25);
			article2.setShipmentCountries("USA,Canada,Mexico");
			article2.setShipmentCost(new BigDecimal("15.00"));
			article2.setRating(4);

			articleRepository.saveAll(Arrays.asList(article1, article2));

			// Adding dummy customers
			Customer customer1 = new Customer();
			customer1.setName("John Doe");
			customer1.setBillingAddress("123 Main St");
			customer1.setEmail("john@example.com");
			customer1.setShipmentAddress("123 Main St");

			Customer customer2 = new Customer();
			customer2.setName("Jane Smith");
			customer2.setBillingAddress("456 Elm St");
			customer2.setEmail("jane@example.com");
			customer2.setShipmentAddress("456 Elm St");

			customerRepository.saveAll(Arrays.asList(customer1, customer2));

			// Adding dummy carts
			Cart cart1 = new Cart();
			cart1.setCustomer(customer1);

			Cart cart2 = new Cart();
			cart2.setCustomer(customer2);

			cartRepository.saveAll(Arrays.asList(cart1, cart2));

			// Adding dummy article carts
			ArticleCart articleCart1 = new ArticleCart();
			articleCart1.setArticle(article1);
			articleCart1.setCart(cart1);
			articleCart1.setAmount(2);

			ArticleCart articleCart2 = new ArticleCart();
			articleCart2.setArticle(article2);
			articleCart2.setCart(cart2);
			articleCart2.setAmount(1);

			articleCartRepository.saveAll(Arrays.asList(articleCart1, articleCart2));

			// Adding dummy payment methods
			PaymentMethod paymentMethod1 = new PaymentMethod();
			paymentMethod1.setProvider("Visa");
			paymentMethod1.setUserIdentifier("john123");
			paymentMethod1.setCardNumber("4111111111111111");
			paymentMethod1.setIban("DE89370400440532013000");
			paymentMethod1.setCheckDigit("123");
			paymentMethod1.setCustomer(customer1);

			PaymentMethod paymentMethod2 = new PaymentMethod();
			paymentMethod2.setProvider("MasterCard");
			paymentMethod2.setUserIdentifier("jane456");
			paymentMethod2.setCardNumber("5500000000000004");
			paymentMethod2.setIban("DE89370400440532013001");
			paymentMethod2.setCheckDigit("456");
			paymentMethod2.setCustomer(customer2);

			paymentMethodRepository.saveAll(Arrays.asList(paymentMethod1, paymentMethod2));

			// Adding dummy retailers
			Retailer retailer1 = new Retailer();
			retailer1.setName("BestBuy");
			retailer1.setAddress("789 Market St");

			Retailer retailer2 = new Retailer();
			retailer2.setName("Amazon");
			retailer2.setAddress("1010 Tech Ave");

			retailerRepository.saveAll(Arrays.asList(retailer1, retailer2));

			// Adding dummy discount codes
			DiscountCode discountCode1 = new DiscountCode();
			discountCode1.setRetailer(retailer1);
			discountCode1.setCode("SAVE10");
			discountCode1.setValidFrom(LocalDate.of(2023, 1, 1));
			discountCode1.setValidTo(LocalDate.of(2023, 12, 31));

			DiscountCode discountCode2 = new DiscountCode();
			discountCode2.setRetailer(retailer2);
			discountCode2.setCode("WELCOME20");
			discountCode2.setValidFrom(LocalDate.of(2023, 6, 1));
			discountCode2.setValidTo(LocalDate.of(2023, 12, 31));

			discountCodeRepository.saveAll(Arrays.asList(discountCode1, discountCode2));

			// Adding dummy retailer articles
			RetailerArticle retailerArticle1 = new RetailerArticle();
			retailerArticle1.setRetailer(retailer1);
			retailerArticle1.setArticle(article1);

			RetailerArticle retailerArticle2 = new RetailerArticle();
			retailerArticle2.setRetailer(retailer2);
			retailerArticle2.setArticle(article2);

			retailerArticleRepository.saveAll(Arrays.asList(retailerArticle1, retailerArticle2));

			// represents the relationship between a retailer and an article.
			// Essentially a mapping that links specific articles to specific retailers,
			// used to manage which articles are sold by which retailers.
		};
	}

}
