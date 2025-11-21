package com.travel.travel_booking_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CreateBookingRequest(
        @NotBlank(message = "Customer name is required")
        @Size(max = 100, message = "Customer name cannot exceeds more than 100 characters")
        String customerName,

        @NotBlank(message = "Destination is required")
        @Size(max=150, message = "Destination cannot exceed more than 150 characters")
        String destination,

        @NotNull(message = "Ammout due is required")
        @Positive(message = "Ammout due must be greater than zero")
        Double amountDue
) {
}
