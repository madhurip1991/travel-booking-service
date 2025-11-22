package com.travel.travel_booking_service.service;

import com.travel.travel_booking_service.dto.PaymentEventDTO;
import com.travel.travel_booking_service.exception.BookingIdNotFound;
import com.travel.travel_booking_service.mapper.PaymentEventsMapper;
import com.travel.travel_booking_service.model.Booking;
import com.travel.travel_booking_service.model.PaymentEvents;
import com.travel.travel_booking_service.repository.BookingRepository;
import com.travel.travel_booking_service.repository.PaymentEventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class PaymentEventService {
    private final PaymentEventRepository paymentEventRepository;
    private final PaymentEventsMapper paymentEventsMapper;
    private final BookingRepository bookingRepository;

    public PaymentEventService(PaymentEventRepository paymentEventRepository, PaymentEventsMapper paymentEventsMapper, BookingRepository bookingRepository) {
        this.paymentEventRepository = paymentEventRepository;
        this.paymentEventsMapper = paymentEventsMapper;
        this.bookingRepository = bookingRepository;
    }

    public List<PaymentEventDTO> getPaymentEventsByBookingId(UUID bookingId) {
        List<PaymentEvents> paymentEventbyBookingId = paymentEventRepository.findByBookingId(bookingId);
        if (paymentEventbyBookingId.isEmpty())
            throw new BookingIdNotFound(bookingId);

        return paymentEventsMapper.toDtoList(paymentEventbyBookingId);
    }

    @Transactional
    public void handleWebhook(PaymentEventDTO paymentEventDTO) {
        if (paymentEventRepository.existsById(paymentEventDTO.transactionId()))
            return;

        paymentEventRepository.save(paymentEventsMapper.toEntity(paymentEventDTO));

        Booking booking = bookingRepository.findById(paymentEventDTO.bookingId())
                .orElseThrow(()->new BookingIdNotFound(paymentEventDTO.bookingId()));
        if (paymentEventDTO.amountPaid()>=booking.getAmountDue())
            booking.setPaymentStatus("PAID");
        bookingRepository.save(booking);
    }
}
