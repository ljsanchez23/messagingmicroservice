package com.foodcourt.MessagingMicroservice.configuration.security;

import com.foodcourt.MessagingMicroservice.configuration.security.jwt.filters.JwtAuthenticationFilter;
import com.foodcourt.MessagingMicroservice.configuration.security.util.AuthenticationEntryPointImpl;
import com.foodcourt.MessagingMicroservice.configuration.security.util.SecurityConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationEntryPointImpl authenticationEntryPointImpl;

    public SecurityConfiguration(JwtAuthenticationFilter jwtAuthenticationFilter, AuthenticationEntryPointImpl authenticationEntryPointImpl) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.authenticationEntryPointImpl = authenticationEntryPointImpl;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(SecurityConstants.NOTIFY_URL).hasRole(SecurityConstants.EMPLOYEE_ROLE)
                        .anyRequest().permitAll()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(authenticationEntryPointImpl))
                .formLogin(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
