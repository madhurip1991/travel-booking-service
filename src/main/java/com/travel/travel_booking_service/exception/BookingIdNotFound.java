package com.travel.travel_booking_service.exception;

import java.util.UUID;

public class BookingIdNotFound extends RuntimeException{

    public BookingIdNotFound(UUID bookingId)  {
        super("Booking not found with id: "+bookingId);
    }
}
