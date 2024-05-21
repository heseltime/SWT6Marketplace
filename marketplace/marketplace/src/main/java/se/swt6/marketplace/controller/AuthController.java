package se.swt6.marketplace.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.swt6.marketplace.dto.LoginRequest;
import se.swt6.marketplace.entity.Customer;
import se.swt6.marketplace.service.CustomerManagement;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private CustomerManagement customerService; // Assume you have a CustomerService to fetch customers

    @Operation(summary = "Authenticate user", description = "Authenticate a user based on email address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully authenticated user",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Customer.class))),
            @ApiResponse(responseCode = "401", description = "Authentication failed",
                    content = @Content)
    })
    @PostMapping("/authenticate")
    public Customer authenticate(
            @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Login request containing email address",
                    required = true,
                    content = @Content(schema = @Schema(implementation = LoginRequest.class))
            ) LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        // Fetch the customer based on email. Implement your logic here.
        Customer customer = customerService.findByEmail(email);
        if (customer == null) {
            //throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authentication failed");
            System.out.println("Did not find customer with email address " + email + " but authenticating anyway");
        } else {
            System.out.println("Found customer: " + customer.getName() + " with email address " +  customer.getEmail());
        }
        return customer;
    }
}
