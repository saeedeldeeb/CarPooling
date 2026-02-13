package com.sawa.gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    @GetMapping("/ride-service")
    public Mono<Map<String, Object>> rideFallback() {
        return buildFallbackResponse("Ride Service is temporarily unavailable");
    }

    @GetMapping("/booking-service")
    public Mono<Map<String, Object>> bookingFallback() {
        return buildFallbackResponse("Booking Service is temporarily unavailable");
    }

    @GetMapping("/payment-service")
    public Mono<Map<String, Object>> paymentFallback() {
        return buildFallbackResponse("Payment Service is temporarily unavailable");
    }

    @GetMapping("/rating-service")
    public Mono<Map<String, Object>> ratingFallback() {
        return buildFallbackResponse("Rating Service is temporarily unavailable");
    }

    private Mono<Map<String, Object>> buildFallbackResponse(String message) {
        return Mono.just(Map.of(
                "timestamp", LocalDateTime.now().toString(),
                "status", HttpStatus.SERVICE_UNAVAILABLE.value(),
                "error", "Service Unavailable",
                "message", message
        ));
    }
}
