package com.quantilearn.api_gateway.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    public static final List<String>  openEndPoints=List.of(
            "/auth",
            "/v3/api-docs",
            "/swagger-ui"
    );

    public Predicate<ServerHttpRequest> isSecured=
            request -> openEndPoints.stream().noneMatch(uri ->request.getURI().getPath().contains(uri));
}