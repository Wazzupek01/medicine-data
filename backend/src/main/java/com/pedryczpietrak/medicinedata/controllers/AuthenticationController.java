package com.pedryczpietrak.medicinedata.controllers;

import com.pedryczpietrak.medicinedata.exceptions.ErrorResponse;
import com.pedryczpietrak.medicinedata.model.DTO.AuthenticationResponseDTO;
import com.pedryczpietrak.medicinedata.model.DTO.UserLoginDTO;
import com.pedryczpietrak.medicinedata.model.DTO.UserRegisterDTO;
import com.pedryczpietrak.medicinedata.model.mappers.AuthenticationResponseMapper;
import com.pedryczpietrak.medicinedata.security.AuthenticationResponse;
import com.pedryczpietrak.medicinedata.services.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Authentication operations, registering new accounts, logging in existing users." +
        " JWT Token is returned as HttpOnly cookie.")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final AuthenticationResponseMapper mapper;

    @Value("${security.key-expiration-time}")
    private int KEY_EXPIRATION_TIME;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService, AuthenticationResponseMapper mapper) {
        this.authenticationService = authenticationService;
        this.mapper = mapper;
    }

    @PostMapping("/register")
    @Operation(summary = "Register", description = "Register new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User registered successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid user credentials", content = @Content)
    })
    public ResponseEntity<AuthenticationResponseDTO> register(@Valid @RequestBody UserRegisterDTO request, HttpServletResponse response) {
        AuthenticationResponse authenticationResponse = authenticationService.register(request);
        response.addCookie(generateJwtCookie(authenticationResponse.getToken()));
        return new ResponseEntity<>(mapper.authenticationResponseToAuthenticationResponseDTO(authenticationResponse), HttpStatus.OK);
    }

    @PostMapping("/login")
    @Operation(summary = "Login", description = "Login user to existing account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User logged in successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid user credentials", content = @Content)
    })
    public ResponseEntity<AuthenticationResponseDTO> login(@Valid @RequestBody UserLoginDTO request, HttpServletResponse response) {
        AuthenticationResponse authenticationResponse = authenticationService.login(request);
        response.addCookie(generateJwtCookie(authenticationResponse.getToken()));
        return new ResponseEntity<>(mapper.authenticationResponseToAuthenticationResponseDTO(authenticationResponse), HttpStatus.OK);
    }

    @GetMapping("/logout")
    @SecurityRequirement(name = "Bearer authentication")
    @Operation(summary = "Logout", description = "Logout active user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User logged out", content = @Content),
    })
    public ResponseEntity<String> logout(HttpServletResponse response) {
        Cookie jwt = new Cookie("jwt", "");
        jwt.setMaxAge(0);
        jwt.setSecure(true);
        jwt.setHttpOnly(true);
        jwt.setPath("/");
        response.addCookie(jwt);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/user")
    @SecurityRequirement(name = "Bearer authentication")
    @Operation(summary = "Get user details", description = "Get email and role of logged in user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationResponseDTO.class))),
            @ApiResponse(responseCode = "403", description = "User not logged in",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<AuthenticationResponseDTO> getActiveUser(@CookieValue("jwt") String jwt) {
        return new ResponseEntity<>(authenticationService.user(jwt), HttpStatus.OK);
    }

    @GetMapping("/admin")
    @SecurityRequirement(name = "Bearer authentication")
    @Operation(summary = "Is admin", description = "Check if active user has role ADMIN")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found", content = @Content(schema = @Schema(implementation = boolean.class))),
            @ApiResponse(responseCode = "403", description = "User not logged in",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<Boolean> isAdmin(@CookieValue("jwt") String jwt) {
        return new ResponseEntity<>(authenticationService.isAdmin(jwt), HttpStatus.OK);
    }

    private Cookie generateJwtCookie(String token){
        Cookie jwt = new Cookie("jwt", token);
        jwt.setMaxAge(KEY_EXPIRATION_TIME/1000);
        jwt.setSecure(true);
        jwt.setHttpOnly(true);
        jwt.setPath("/");
        return jwt;
    }
}