package com.example.hrmsproject.security;

import com.example.hrmsproject.security.JwtAuthenticationFilter;
import com.example.hrmsproject.service.impl.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter,
                          CustomUserDetailsService customUserDetailsService) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider =
                new DaoAuthenticationProvider(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/users/login", "/api/users/register").permitAll()
                        .requestMatchers("/api/clients/**").hasRole("SYSTEM_ADMIN")
                        .requestMatchers("/api/users/**").hasAnyRole("SYSTEM_ADMIN", "EMPLOYER")
                        .requestMatchers("/api/departments/**").hasAnyRole("SYSTEM_ADMIN", "EMPLOYER")
                        .requestMatchers("/api/employee-types/**").hasAnyRole("SYSTEM_ADMIN", "EMPLOYER")
                        .requestMatchers("/api/leave-types/**").hasAnyRole("SYSTEM_ADMIN", "EMPLOYER")
                        .requestMatchers("/api/employees/**").hasAnyRole("SYSTEM_ADMIN", "EMPLOYER", "EMPLOYEE")
                        .requestMatchers("/api/employee-emergency-contacts/**").hasAnyRole("SYSTEM_ADMIN", "EMPLOYER", "EMPLOYEE")
                        .requestMatchers("/api/leave-allocations/**").hasAnyRole("SYSTEM_ADMIN", "EMPLOYER", "EMPLOYEE")
                        .requestMatchers("/api/leave-requests/**").hasAnyRole("SYSTEM_ADMIN", "EMPLOYER", "EMPLOYEE")
                        .requestMatchers("/api/clock-in-out/**").hasAnyRole("SYSTEM_ADMIN", "EMPLOYER", "EMPLOYEE")
                        .requestMatchers("/api/expenses/**").hasAnyRole("SYSTEM_ADMIN", "EMPLOYER", "EMPLOYEE")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}