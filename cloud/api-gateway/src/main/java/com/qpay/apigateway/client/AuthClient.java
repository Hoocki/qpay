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

    public static final String VALIDATION_PATH = "http://auth/api/v1/auth/validate";

    private final WebClient lbWebClient;

    public Mono<Void> validateToken(final String headerAuth) {
        return lbWebClient.get()
                .uri(VALIDATION_PATH)
                .header(HttpHeaders.AUTHORIZATION, headerAuth)
                .retrieve().bodyToMono(Void.class)
                .onErrorResume(err -> Mono.error(new AuthorizationException(err.getMessage())));
    }
}
