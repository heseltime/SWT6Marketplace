package se.swt6.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.swt6.marketplace.entity.PaymentMethod;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Integer> {
}

