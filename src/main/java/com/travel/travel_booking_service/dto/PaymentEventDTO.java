package com.travel.travel_booking_service.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record PaymentEventDTO(
        UUID transactionId,
        UUID bookingId,
        Double amountPaid,
        LocalDateTime paymentTime
) {
}
