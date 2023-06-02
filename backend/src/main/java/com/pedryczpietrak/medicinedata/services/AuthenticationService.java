package com.pedryczpietrak.medicinedata.services;

import com.pedryczpietrak.medicinedata.exceptions.EmailExistsException;
import com.pedryczpietrak.medicinedata.model.DTO.UserLoginDTO;
import com.pedryczpietrak.medicinedata.model.DTO.UserRegisterDTO;
import com.pedryczpietrak.medicinedata.model.User;
import com.pedryczpietrak.medicinedata.repositories.UserRepository;
import com.pedryczpietrak.medicinedata.security.AuthenticationResponse;
import com.pedryczpietrak.medicinedata.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                                 JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(UserRegisterDTO request) {
        if(userRepository.findUserByEmail(request.getEmail()).isPresent()){
            throw new EmailExistsException();
        }
        User user = new User(request.getEmail(), passwordEncoder.encode(request.getPassword()), request.getRole());
        userRepository.save(user);
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("ROLE", user.getRole());
        String jwtToken = jwtService.generateToken(extraClaims, user);
        return new AuthenticationResponse(jwtToken, user.getEmail(), user.getRole());
    }

    public AuthenticationResponse authenticate(UserLoginDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()
                )
        );

        User user =  userRepository.findUserByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException(request.getEmail()));
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("ROLE", user.getRole());
        String jwtToken = jwtService.generateToken(extraClaims, user);
        return new AuthenticationResponse(jwtToken, user.getEmail(), user.getRole());
    }
}
