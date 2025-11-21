package com.travel.travel_booking_service.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name="bookings")
public class Booking {
    @Id
    @GeneratedValue
    private UUID bookingId;

    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false)
    private String destination;

    @Column(nullable = false)
    private Double amountDue;

    @Column(nullable = false)
    private String paymentStatus;

    public Booking() {
    }

    public Booking(String customerName, String destination, String paymentStatus, Double amountDue) {
//        this.bookingId = bookingId;
        this.customerName = customerName;
        this.destination = destination;
        this.paymentStatus = "PENDING";
        this.amountDue = amountDue;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", customerName='" + customerName + '\'' +
                ", destination='" + destination + '\'' +
                ", amountDue=" + amountDue +
                ", paymentStatus='" + paymentStatus + '\'' +
                '}';
    }

    public UUID getBookingId() {
        return bookingId;
    }

    public void setBookingId(UUID bookingId) {
        this.bookingId = bookingId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Double getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(Double amountDue) {
        this.amountDue = amountDue;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
