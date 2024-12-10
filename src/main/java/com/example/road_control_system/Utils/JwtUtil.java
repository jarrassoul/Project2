package com.example.road_control_system.Utils;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.road_control_system.Utils.JwtUtil.ClaimsResolver;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class JwtUtil {

    // Generate a JWT Token
  public String generateToken(String username) {
    // Use HS256 or other algorithms (HS384, HS512) from Java JWT (Auth0)
    Algorithm algorithm = Algorithm.HMAC256(SecuirityConstants.Jwt_SECRET.getEncoded());  // HMAC256 with the byte-encoded secret key

    return JWT.create()
            .withSubject(username)
            .withIssuedAt(new Date())
            .withExpiresAt(new Date(System.currentTimeMillis() + SecuirityConstants.Expiration_DATE))  // expiration time
            .sign(algorithm);  // sign the token with the algorithm
}


    // Extract Username from JWT
   public String extractUsername(String token) {
    DecodedJWT decodedJWT = JWT.decode(token);  // Decode the token
    return decodedJWT.getSubject();  // Get the subject from the decoded token
}
    // Extract any claim from the JWT
    // Extract a claim from the token
    private <T> T extractClaim(String token, ClaimsResolver<T> claimsResolver) {
        DecodedJWT decodedJWT = JWT.decode(token);  // Decode the JWT token
        return claimsResolver.resolve(decodedJWT);
    }

    // Extract all claims from the JWT
    private DecodedJWT extractAllClaims(String token) {
        return JWT.decode(token);  // Decode the token to get all claims
    }

    // Validate the JWT token (check if it is expired)
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Extract expiration date from the token
    private Date extractExpiration(String token) {
        DecodedJWT decodedJWT = extractAllClaims(token);
        return decodedJWT.getExpiresAt();  // Extract the expiration date
    }

    // Check if the token is valid (not expired)
    public boolean validateToken(String token) {
        return !isTokenExpired(token);
    }

    // Resolve the token from the HTTP request header
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);  // Extract the token part after "Bearer "
        }
        return null;
    }

    // Interface to resolve claims from the JWT
    @FunctionalInterface
    public interface ClaimsResolver<T> {
        T resolve(DecodedJWT decodedJWT);
    }


    // Example usage for extracting expiration date
    public Date extractExpirationDate(String token) {
        return extractClaim(token, DecodedJWT::getExpiresAt);  // Extract expiration from the token
    }
}
