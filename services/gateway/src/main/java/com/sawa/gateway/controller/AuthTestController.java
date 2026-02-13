package com.sawa.gateway.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class AuthTestController {

    @SuppressWarnings("unchecked")
    @GetMapping("/auth/test")
    public Mono<Map<String, Object>> testAuth(@AuthenticationPrincipal Jwt jwt) {
        Map<String, Object> realmAccess = jwt.getClaim("realm_access");
        List<String> roles = realmAccess != null
                ? (List<String>) realmAccess.getOrDefault("roles", Collections.emptyList())
                : Collections.emptyList();

        return Mono.just(Map.of(
                "subject", jwt.getSubject(),
                "email", jwt.getClaimAsString("email") != null ? jwt.getClaimAsString("email") : "N/A",
                "name", jwt.getClaimAsString("preferred_username") != null ? jwt.getClaimAsString("preferred_username") : "N/A",
                "roles", roles,
                "issued_at", Objects.requireNonNull(jwt.getIssuedAt()).toString(),
                "expires_at", Objects.requireNonNull(jwt.getExpiresAt()).toString()
        ));
    }
}
