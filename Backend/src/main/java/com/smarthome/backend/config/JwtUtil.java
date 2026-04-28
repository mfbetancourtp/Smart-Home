package com.smarthome.backend.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    // Cambiamos el secreto a uno coherente con el proyecto y lo suficientemente fuerte
    // En producción, esto debe inyectarse con @Value("${jwt.secret}") desde application.properties
    private final String secret = "SmartHome120m2_ClaveSecreta_ProyectoPoligran_2026_NoCompartir";

    // -------------------------------------------------------------------
    // 0. NUEVO: Generar el token (Requerido por el AuthController)
    // -------------------------------------------------------------------
    public String generarToken(String correo) {
        long expirationTime = 86400000; // 24 horas en milisegundos
        return Jwts.builder()
                .setSubject(correo) // Usamos el correo como identificador principal
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    // -------------------------------------------------------------------
    // 1. Validar si el token es legítimo (Tu código espejo)
    // -------------------------------------------------------------------
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return !isTokenExpired(token);
        } catch (Exception e) {
            System.err.println("❌ ERROR VALIDANDO TOKEN: " + e.getMessage());
            return false;
        }
    }

    // -------------------------------------------------------------------
    // 2. Extraer el username / correo del token (Tu código espejo)
    // -------------------------------------------------------------------
    public String extractUsername(String token) {
        try {
            return getClaimFromToken(token, Claims::getSubject);
        } catch (Exception e) {
            return null;
        }
    }

    // -------------------------------------------------------------------
    // Métodos de apoyo internos
    // -------------------------------------------------------------------
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