package com.qpay.apigateway.client;

import com.qpay.apigateway.exception.AuthorizationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AuthClient {

    public static final String VALIDATION_PATH = "/api/v1/auth/validate";

    private final WebClient webClient;

    public Mono<Void> validateToken(final String token) {
        return webClient.get()
                .uri(VALIDATION_PATH)
                .header(HttpHeaders.AUTHORIZATION, token)
                .retrieve()
                .bodyToMono(Void.class)
                .onErrorResume(error -> Mono.error(new AuthorizationException(error.getMessage())));
    }
}
