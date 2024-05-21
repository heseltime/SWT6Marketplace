package se.swt6.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.swt6.marketplace.entity.Retailer;

public interface RetailerRepository extends JpaRepository<Retailer, Integer> {
}
