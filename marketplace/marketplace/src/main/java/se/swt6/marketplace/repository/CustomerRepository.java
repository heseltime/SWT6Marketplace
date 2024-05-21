package se.swt6.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.swt6.marketplace.entity.Customer;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByEmail(String email);
}
