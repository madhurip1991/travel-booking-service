package com.travel.travel_booking_service.repository;

import com.travel.travel_booking_service.model.PaymentEvents;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PaymentEventRepository extends JpaRepository<PaymentEvents, UUID> {
    List<PaymentEvents> findByBookingId(UUID bookingId);
}
