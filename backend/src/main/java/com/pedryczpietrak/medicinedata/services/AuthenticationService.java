package com.pedryczpietrak.medicinedata.services;

import com.pedryczpietrak.medicinedata.exceptions.EmailExistsException;
import com.pedryczpietrak.medicinedata.exceptions.InvalidJwtException;
import com.pedryczpietrak.medicinedata.exceptions.NotMatchingPasswordException;
import com.pedryczpietrak.medicinedata.exceptions.RoleNotFoundException;
import com.pedryczpietrak.medicinedata.model.DTO.AuthenticationResponseDTO;
import com.pedryczpietrak.medicinedata.model.DTO.UserLoginDTO;
import com.pedryczpietrak.medicinedata.model.DTO.UserRegisterDTO;
import com.pedryczpietrak.medicinedata.model.entities.Role;
import com.pedryczpietrak.medicinedata.model.entities.User;
import com.pedryczpietrak.medicinedata.repositories.RoleRepository;
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
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationService(UserRepository userRepository, RoleRepository roleRepository,
                                 PasswordEncoder passwordEncoder, JwtService jwtService,
                                 AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(UserRegisterDTO request) {
        if(!request.getPassword().equals(request.getRepeatPassword())) throw new NotMatchingPasswordException();

        if (userRepository.findUserByEmail(request.getEmail()).isPresent()) {
            throw new EmailExistsException();
        }

        Role role = roleRepository.findRoleByName(request.getRole())
                .orElseThrow(() -> new RoleNotFoundException(request.getRole()));

        User user = new User(request.getEmail(), passwordEncoder.encode(request.getPassword()), role);
        userRepository.save(user);
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("ROLE", user.getRole());
        String jwtToken = jwtService.generateToken(extraClaims, user);
        return new AuthenticationResponse(jwtToken, user.getEmail(), user.getRole());
    }

    public AuthenticationResponse login(UserLoginDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()
                )
        );

        User user = userRepository.findUserByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException(request.getEmail()));
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("ROLE", user.getRole());
        String jwtToken = jwtService.generateToken(extraClaims, user);
        return new AuthenticationResponse(jwtToken, user.getEmail(), user.getRole());
    }

    public AuthenticationResponseDTO user(String jwt) {
        String email = jwtService.extractUsername(jwt);
        User user = userRepository.findUserByEmail(email).orElseThrow(InvalidJwtException::new);
        return new AuthenticationResponseDTO(email, user.getRole());
    }

    public boolean isAdmin(String jwt) {
        String email = jwtService.extractUsername(jwt);
        User user = userRepository.findUserByEmail(email).orElseThrow(InvalidJwtException::new);
        return user.getRole().getName().equals("ROLE_ADMIN");
    }
}
