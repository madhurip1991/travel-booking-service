package com.travel.travel_booking_service.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record PaymentEventDTO(
        @NotNull(message = "transactionId is required")
        UUID transactionId,

        @NotNull(message = "bookingId is required")
        UUID bookingId,

        @NotNull(message = "amountPaid is required")
        Double amountPaid,

        @NotNull(message = "paymentTime is required")
        LocalDateTime paymentTime
) {
}
