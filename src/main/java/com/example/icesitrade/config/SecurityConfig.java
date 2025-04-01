package com.example.icesitrade.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/users/**").hasAuthority("READ_USERS")
                        .requestMatchers("/api/roles/**").hasAuthority("MANAGE_ROLES")
                        .requestMatchers("/api/permissions/**").hasAuthority("MANAGE_ROLES")
                        .requestMatchers("/api/favorites/**").authenticated()
                        .requestMatchers("/api/products/**").hasAnyAuthority("CREATE_PRODUCT", "DELETE_PRODUCT")
                        .requestMatchers("/api/messages/**").authenticated()
                        .requestMatchers("/api/ratings/**").authenticated()
                        .requestMatchers("/api/categories/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/products/**").hasAuthority("product:update")
                        .anyRequest().permitAll()
                )
                .httpBasic(); // Para pruebas rápidas con Postman

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // SOLO PARA DESARROLLO
    }
}
