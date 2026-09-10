package com.example.travelsystem.security;

import com.example.travelsystem.service.ServiceImpl.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .userDetailsService(customUserDetailsService)

                .authorizeHttpRequests(auth -> auth

                        // Login, Register və static fayllar
                        .requestMatchers(
                                "/auth/**",
                                "/css/**",
                                "/js/**",
                                "/images/**",
                                "/webjars/**"
                        ).permitAll()

                        // Yalnız ADMIN
                        .requestMatchers("/admin/**")
                        .hasRole("ADMIN")

                        // USER və ADMIN daxil olduqdan sonra
                        .requestMatchers(
                                "/",
                                "/about",
                                "/about/**",
                                "/blog/**",
                                "/contact/**",
                                "/hotels/**",
                                "/rooms/**",
                                "/countries/**",
                                "/country/**",
                                "/cities/**",
                                "/amenities/**",
                                "/reservations/**"
                        )
                        .hasAnyRole("USER", "ADMIN")

                        .anyRequest().authenticated()
                )

                .formLogin(login -> login
                        .loginPage("/auth/login")
                        .loginProcessingUrl("/auth/login")
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/auth/login?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )

                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}