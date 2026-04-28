package com.smarthome.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtRequestFilter jwtRequestFilter;

    public SecurityConfig(JwtRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    // =========================================================
    // LA MAGIA ESTÁ AQUÍ: Ignoramos por completo las rutas viejas
    // =========================================================
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // Spring Security se apagará completamente para estas rutas:
        return (web) -> web.ignoring().antMatchers("/api/documento/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().configurationSource(corsConfigurationSource()) 
            .and().csrf().disable()
            .authorizeRequests()
                // EL CONTROLADOR NUEVO QUEDA PROTEGIDO CON TOKEN
                .antMatchers("/api/adminregla/**").authenticated() 
                .anyRequest().permitAll() 
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

@Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // 1. Aquí ponemos explícitamente los orígenes permitidos
        configuration.setAllowedOrigins(Arrays.asList(
            "http://localhost:4200", 
            "https://extraordinary-axolotl-729bce.netlify.app" // ⚠️ CAMBIA ESTO por tu URL real de Netlify final (sin la barra / al final)
        )); 
        
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"));
        
        // 2. Permitimos todos los headers para evitar bloqueos extraños de Angular
        configuration.setAllowedHeaders(Arrays.asList("*")); 
        
        // 3. Credenciales en true (como lo tenías, que está bien)
        configuration.setAllowCredentials(true); 
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); 
        
        return source;
    }
}