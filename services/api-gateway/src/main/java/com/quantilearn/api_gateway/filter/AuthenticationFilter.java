package com.quantilearn.api_gateway.filter;

import com.quantilearn.api_gateway.security.JwtService;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator routeValidator;

    @Autowired
    private JwtService jwtService;

    public AuthenticationFilter(){
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (((exchange, chain) -> {
            if(routeValidator.isSecured.test(exchange.getRequest())){
                if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
                    throw new RuntimeException("Authorization Header required!");
                }

                String authHeader= Objects.requireNonNull(exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION)).getFirst();

                if(authHeader!=null && authHeader.startsWith("Bearer ")){
                    String token=authHeader.substring(7);

                    if(jwtService.isTokenExpired(token)&&!jwtService.isTokenValid(token)){
                        throw new RuntimeException("Invalid token!");
                    }
                }
                else{
                    throw new RuntimeException("Invalid Token Format");
                }
            }
            return chain.filter(exchange);
        }));
    }

    public static class Config{

    }

}