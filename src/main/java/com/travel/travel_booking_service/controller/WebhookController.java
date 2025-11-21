package com.travel.travel_booking_service.controller;

import com.travel.travel_booking_service.dto.PaymentEventDTO;
import com.travel.travel_booking_service.security.JwtUtil;
import com.travel.travel_booking_service.service.PaymentEventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebhookController {
    private final PaymentEventService paymentEventService;

    public WebhookController(PaymentEventService paymentEventService) {
        this.paymentEventService = paymentEventService;
    }

    @PostMapping("/webhooks/payments")
    public ResponseEntity<String> receiveAndProcess(
            @RequestHeader("Authorization") String authHeader,
            @Validated @RequestBody PaymentEventDTO paymentEventDTO)    {
        try {
            if (!authHeader.startsWith("Bearer"))
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Authorization header");

            String token = authHeader.substring(7);
            JwtUtil.validateToken(token);
            paymentEventService.handleWebhook(paymentEventDTO);
            return ResponseEntity.ok("Webhook Processed");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden: " + e.getMessage());
        }
    }
}
