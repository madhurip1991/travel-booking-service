package com.travel.travel_booking_service.service;

import com.travel.travel_booking_service.dto.BookingResponse;
import com.travel.travel_booking_service.dto.CreateBookingRequest;
import com.travel.travel_booking_service.exception.BookingIdNotFound;
import com.travel.travel_booking_service.exception.DataNotFoundException;
import com.travel.travel_booking_service.mapper.BookingMapper;
import com.travel.travel_booking_service.model.Booking;
import com.travel.travel_booking_service.repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    public BookingService(BookingRepository bookingRepository, BookingMapper bookingMapper) {
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
    }

    public BookingResponse createBooking(CreateBookingRequest bookingRequest) {
        Booking booking1=bookingMapper.toEntity(bookingRequest);
        Booking booking=bookingRepository.save(booking1);
        return bookingMapper.toDto(booking);
    }

    public BookingResponse getBookingById(UUID bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(()->new BookingIdNotFound(bookingId));

        return bookingMapper.toDto(booking);
    }

    public List<BookingResponse> findAllBookings() {
        List<Booking> allBookings = bookingRepository.findAll();
        if(allBookings.isEmpty())
            throw new DataNotFoundException("There are no bookings");

        return bookingMapper.toDtoList(allBookings);
    }
}
