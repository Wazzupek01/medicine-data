package com.pedryczpietrak.medicinedata.config;

import com.pedryczpietrak.medicinedata.security.ExceptionFilter;
import com.pedryczpietrak.medicinedata.security.JwtAuthenticationFilter;
import com.pedryczpietrak.medicinedata.security.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final ExceptionFilter exceptionFilter;
    private final AuthenticationProvider authenticationProvider;

    private final CorsConfigurationSource corsConfigurationSource;

    @Autowired
    public SecurityConfiguration(JwtAuthenticationFilter jwtAuthenticationFilter, ExceptionFilter exceptionFilter, AuthenticationProvider authenticationProvider, CorsConfigurationSource corsConfigurationSource) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.exceptionFilter = exceptionFilter;
        this.authenticationProvider = authenticationProvider;
        this.corsConfigurationSource = corsConfigurationSource;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests.requestMatchers(SecurityConstants.WHITELIST).hasAnyRole("ADMIN", "USER"))
//                .sessionManagement((sessionManagement) ->
//                        sessionManagement
//                                .sessionConcurrency((sessionConcurrency) ->
//                                        sessionConcurrency
//                                                .maximumSessions(1)
//                                                .expiredUrl("/login?expired")
//                                ))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(exceptionFilter, JwtAuthenticationFilter.class);
        return http.build();
    }
}