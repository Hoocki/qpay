package com.qpay.apigateway.filter;

import com.qpay.apigateway.client.AuthClient;
import com.qpay.apigateway.exception.AuthorizationException;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import java.util.Set;

@Component
public class AuthenticationGatewayFilterFactory extends
        AbstractGatewayFilterFactory<AuthenticationGatewayFilterFactory.Config> {

    private final AuthClient authClient;

    private static final Set<String> ALLOWED_PATHS = Set.of(
            "/api/v1/customers/signup",
            "/api/v1/merchants/signup",
            "/api/v1/auth/singIn");

    public AuthenticationGatewayFilterFactory(final AuthClient authClient) {
        super(Config.class);
        this.authClient = authClient;
    }

    @Override
    public GatewayFilter apply(final Config config) {
        return (exchange, chain) -> {
            final var request = exchange.getRequest();
            final var path = request.getPath().toString();
            if (isPathAllowed(path)) {
                return chain.filter(exchange);
            }
            if (!authHeaderExists(request)) {
                throw new AuthorizationException("Missing authorization header");
            }
            final var token = request.getHeaders().getOrEmpty(HttpHeaders.AUTHORIZATION).getFirst();
            return authClient
                    .validateToken(token)
                    .then(chain.filter(exchange));
        };
    }

    public static class Config {
    }

    private boolean isPathAllowed(final String path) {
        return ALLOWED_PATHS.stream()
                .anyMatch(path::contains);
    }

    private boolean authHeaderExists(final ServerHttpRequest request) {
        return request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION);
    }
}