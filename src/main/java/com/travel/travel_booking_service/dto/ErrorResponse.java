package com.travel.travel_booking_service.dto;

import java.time.Instant;
import java.util.List;

public record ErrorResponse (
    Instant timestamp,
    int status,
    String error,
    String message,
    String path,
    List<String> details
){
}
