package se.swt6.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.swt6.marketplace.entity.DiscountCode;

public interface DiscountCodeRepository extends JpaRepository<DiscountCode, Integer> {
}
