package com.travel.travel_booking_service.dto;

import java.util.UUID;

public record BookingResponse(
        UUID bookingId,
        String customerName,
        String destination,
        Double amountDue,
        String paymentStatus
) {
}
