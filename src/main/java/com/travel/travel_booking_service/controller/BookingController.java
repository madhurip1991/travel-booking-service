package com.travel.travel_booking_service.controller;

import com.travel.travel_booking_service.dto.BookingResponse;
import com.travel.travel_booking_service.dto.CreateBookingRequest;
import com.travel.travel_booking_service.dto.PaymentEventDTO;
import com.travel.travel_booking_service.mapper.BookingMapper;
import com.travel.travel_booking_service.service.BookingService;
import com.travel.travel_booking_service.service.PaymentEventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class BookingController {
    private final BookingService bookingService;
    private final BookingMapper bookingMapper;
    private final PaymentEventService paymentService;

    public BookingController(BookingService bookingService, BookingMapper bookingMapper, PaymentEventService paymentService) {
        this.bookingService=bookingService;
        this.bookingMapper = bookingMapper;
        this.paymentService = paymentService;
    }

    @PostMapping("/api/bookings")
    public ResponseEntity<BookingResponse> createBooking(@Validated @RequestBody CreateBookingRequest bookingRequest)  {
        BookingResponse booking = bookingService.createBooking(bookingRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(booking);
    }

    @GetMapping("/api/bookings/{bookingId}")
    public BookingResponse getBooking(@PathVariable UUID bookingId) {
        return bookingService.getBookingById(bookingId);
    }

    @GetMapping("/api/bookings")
    public List<BookingResponse> findAllBookings()  {
        return bookingService.findAllBookings();
    }

    @GetMapping("/api/bookings/{bookingId}/payments")
    public List<PaymentEventDTO> getPaymentEvents(@PathVariable UUID bookingId)   {
        return paymentService.getPaymentEventsByBookingId(bookingId);
    }
}
