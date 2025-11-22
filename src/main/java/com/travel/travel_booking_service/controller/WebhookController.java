package com.travel.travel_booking_service.controller;

import com.travel.travel_booking_service.dto.PaymentEventDTO;
import com.travel.travel_booking_service.security.JwtUtil;
import com.travel.travel_booking_service.service.PaymentEventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger log= LoggerFactory.getLogger(WebhookController.class);

    public WebhookController(PaymentEventService paymentEventService) {
        this.paymentEventService = paymentEventService;
    }

    @PostMapping("/webhooks/payments")
    public ResponseEntity<String> receiveAndProcess(
            @RequestHeader("Authorization") String authHeader,
            @Validated @RequestBody PaymentEventDTO paymentEventDTO)    {
        try {

            log.info("Payload : "+paymentEventDTO.toString());
            log.info("authHeader : "+authHeader);
            if (!authHeader.startsWith("Bearer"))
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Authorization header");

            String token = authHeader.substring(7);
            JwtUtil.validateToken(token);
            log.info("Token Validated");

            paymentEventService.handleWebhook(paymentEventDTO);
            return ResponseEntity.ok("Webhook Processed");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden: " + e.getMessage());
        }
    }
}
