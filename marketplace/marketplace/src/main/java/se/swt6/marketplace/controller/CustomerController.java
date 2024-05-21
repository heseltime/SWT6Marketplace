package se.swt6.marketplace.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.swt6.marketplace.entity.Customer;
import se.swt6.marketplace.service.CustomerManagement;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {

    @Autowired
    private CustomerManagement customerService;

    @Operation(summary = "Get all customers", description = "Retrieve a list of all customers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Customer.class)))
    })
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @Operation(summary = "Get customer by ID", description = "Retrieve a customer by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved customer",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Customer.class))),
            @ApiResponse(responseCode = "404", description = "Customer not found",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Integer id) {
        return customerService.getCustomerById(id);
    }

    @Operation(summary = "Create a new customer", description = "Create a new customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created customer",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Customer.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input",
                    content = @Content)
    })
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @Operation(summary = "Update a customer", description = "Update an existing customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated customer",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Customer.class))),
            @ApiResponse(responseCode = "404", description = "Customer not found",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Integer id, @RequestBody Customer customer) {
        customer.setCustomerId(id);
        return customerService.updateCustomer(customer);
    }

    @Operation(summary = "Delete a customer", description = "Delete a customer by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted customer",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Customer not found",
                    content = @Content)
    })
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Integer id) {
        customerService.deleteCustomer(id);
    }
}
