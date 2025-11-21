package com.travel.travel_booking_service.mapper;

import com.travel.travel_booking_service.dto.BookingResponse;
import com.travel.travel_booking_service.dto.CreateBookingRequest;
import com.travel.travel_booking_service.model.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel="spring")
public interface BookingMapper {

    @Mapping(target = "bookingId", ignore = true)  // UUID auto-generated
    @Mapping(target = "paymentStatus", constant = "PENDING")
    Booking toEntity(CreateBookingRequest bookingRequest);

    BookingResponse toDto(Booking booking);

    List<BookingResponse> toDtoList(List<Booking> bookings);

}
