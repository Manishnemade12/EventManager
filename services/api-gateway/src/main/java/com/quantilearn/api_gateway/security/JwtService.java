package com.quantilearn.api_gateway.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${security.jwt.secret-key}")
    private String key;

    @Value("${security.jwt.expiration-time}")
    private long expiration;

    public long getTokenExpiration(){
        return this.expiration;
    }

    private SecretKey getSignInKey(){
        byte[] keyBytes= Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUsername(String token){
        return extractClaims(token,Claims::getSubject);
    }

    private <T> T extractClaims(String token, Function<Claims,T> claimsTFunction){
        final Claims claims=extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    private Claims extractAllClaims(String token){
         return Jwts
                 .parser()
                 .verifyWith(getSignInKey())
                 .build()
                 .parseSignedClaims(token)
                 .getPayload();
    }


    public boolean isTokenValid(String token){
        try {

            // SECRET_KEY is your Base64-encoded HMAC secret string (env/config)
            byte[] keyBytes = Decoders.BASE64.decode(key);
            SecretKey key = Keys.hmacShaKeyFor(keyBytes);

            // New API: parser() -> verifyWith(key) -> parseSignedClaims(token)
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);

            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false; // invalid, expired, or signature doesn’t match
        }
    }

    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token){
        return  extractClaims(token,Claims::getExpiration);
    }


}
