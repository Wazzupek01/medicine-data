package com.pedryczpietrak.medicinedata.security;

public class SecurityConstants {
    public static final String[] WHITELIST = {
            "/documentation",
            "/documentation/**",
            "/auth/login",
            "/auth/register",
            "/produkt/name/**",
            "/swagger-ui.html", "/docs", "/swagger-resources/**",
            "/swagger-resources", "/v3/api-docs/**", "/swagger-ui/**",
            "/swagger-ui/favicon-32x32.png", "/swagger-ui/favicon-16x16.png",
            "/webjars/swagger-ui/**", "/proxy/**"
    };

    public static final String[] ADMIN_ONLY = {
            "/produkt/delete/**", "/produkt/update",
            "/produkt/xml", "/produkt/json"
    };
}
