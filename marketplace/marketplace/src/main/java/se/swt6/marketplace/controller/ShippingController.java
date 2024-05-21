package se.swt6.marketplace.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.swt6.marketplace.dto.ShippingRequest;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class ShippingController {

    @Operation(summary = "Calculate shipping cost", description = "Calculates the shipping cost based on the ZIP code and quantity.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Shipping cost calculated successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Map.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping("/calculateShipping")
    public ResponseEntity<Map<String, Object>> calculateShipping(
            @Parameter(description = "Shipping request containing ZIP code and quantity", required = true)
            @RequestBody ShippingRequest request) {

        double shippingCost = calculateShippingCost(request.getZipCode(), request.getQuantity());

        Map<String, Object> response = new HashMap<>();
        response.put("shippingCost", shippingCost);

        return ResponseEntity.ok(response);
    }

    private double calculateShippingCost(String zipCode, int quantity) {
        // Placeholder logic for shipping cost calculation
        // Replace this with actual logic based on your requirements
        double baseCost = 5.0;
        double costPerItem = 2.0;
        return baseCost + (costPerItem * quantity);
    }
}
