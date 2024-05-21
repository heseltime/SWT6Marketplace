package se.swt6.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.swt6.marketplace.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}

