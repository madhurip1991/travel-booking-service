package com.travel.travel_booking_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name="payment_events")
public class PaymentEvents {

    @Id
    @Column(nullable = false)
    private UUID transactionId;

    @Column(nullable = false)
    private UUID bookingId;

    @Column(nullable = false)
    private Double amountPaid;

    @Column(nullable = false)
    private LocalDateTime paymentTime;

    public PaymentEvents() {
    }
    public PaymentEvents(UUID bookingId, Double amountPaid, LocalDateTime paymentTime) {
        this.bookingId = bookingId;
        this.amountPaid = amountPaid;
        this.paymentTime = paymentTime;
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
    }

    public UUID getBookingId() {
        return bookingId;
    }

    public void setBookingId(UUID bookingId) {
        this.bookingId = bookingId;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(LocalDateTime paymentTime) {
        this.paymentTime = paymentTime;
    }

    @Override
    public String toString() {
        return "PaymentEvents{" +
                "transactionId=" + transactionId +
                ", bookingId=" + bookingId +
                ", amountPaid=" + amountPaid +
                ", paymentTime=" + paymentTime +
                '}';
    }
}
