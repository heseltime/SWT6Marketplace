package se.swt6.marketplace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.swt6.marketplace.entity.Customer;
import se.swt6.marketplace.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerManagement {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Integer id) {
        return customerRepository.findById(id).orElse(null);
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Integer id) {
        customerRepository.deleteById(id);
    }

    // for auth mock/test
    public Customer findByEmail(String email) {
        Optional<Customer> customer = customerRepository.findByEmail(email);
        return customer.orElse(null);
    }
}
