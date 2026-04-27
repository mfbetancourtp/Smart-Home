package com.smarthome.backend.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    // Usamos EXACTAMENTE la misma clave secreta que el otro proyecto
    private final String secret = "ClaveUltraSecretaDeColpensiones2026_NoCompartir_SeguridadSRE";

    // 1. Validar si el token es legítimo (Espejo del otro proyecto)
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return !isTokenExpired(token);
        } catch (Exception e) {
            System.err.println("❌ ERROR VALIDANDO TOKEN: " + e.getMessage());
            return false;
        }
    }

    // 2. Extraer el username del token (Espejo del otro proyecto)
    public String extractUsername(String token) {
        try {
            return getClaimFromToken(token, Claims::getSubject);
        } catch (Exception e) {
            return null;
        }
    }

    // Métodos de apoyo internos (Idénticos al otro proyecto)
    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return claimsResolver.apply(claims);
    }

    private boolean isTokenExpired(String token) {
        try {
            final Date expiration = getClaimFromToken(token, Claims::getExpiration);
            return expiration.before(new Date());
        } catch (Exception e) {
            return true; // Si explota sacando la fecha, asumimos que expiró/es inválido
        }
    }
}