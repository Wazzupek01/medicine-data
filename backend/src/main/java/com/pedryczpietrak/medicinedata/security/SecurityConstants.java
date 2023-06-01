package com.pedryczpietrak.medicinedata.security;

public class SecurityConstants {
    public static final String[] WHITELIST = {
            "/documentation/**",
            "/auth/**",
            "auth/authenticate",
            "/swagger-ui.html", "/docs", "/swagger-resources/**",
            "/swagger-resources", "/v3/api-docs/**", "/swagger-ui/**",
            "/swagger-ui/favicon-32x32.png", "/swagger-ui/favicon-16x16.png",
            "/webjars/swagger-ui/**", "/proxy/**"
    };
}
