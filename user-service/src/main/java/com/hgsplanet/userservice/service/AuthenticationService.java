package com.hgsplanet.userservice.service;

import com.hgsplanet.userservice.dao.UserRepository;
import com.hgsplanet.userservice.documents.User;
import com.hgsplanet.userservice.dto.BusinessDto;
import com.hgsplanet.userservice.dto.UserDto;
import com.hgsplanet.userservice.enums.Role;
import com.hgsplanet.userservice.model.AuthenticationRequest;
import com.hgsplanet.userservice.model.AuthenticationResponse;
import com.hgsplanet.userservice.model.BusinessRegisterRequest;
import com.hgsplanet.userservice.model.RegisterRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.AuthenticationException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, UserService userService, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .profileImgPath(request.getProfileImgPath())
                .creationTime(LocalDateTime.now())
                .phoneNum(request.getPhoneNum())
                .roles(List.of(Role.USER))
                .build();
        userService.addUser(UserDto.toDto(user));

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse registerBusiness(BusinessRegisterRequest request) {
        var business = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .profileImgPath(request.getProfileImgPath())
                .creationTime(LocalDateTime.now())
                .phoneNum(request.getPhoneNum())
                .roles(List.of(Role.BUSINESS))
                .city(request.getCity())
                .openingTime(request.getOpeningTime())
                .closingTime(request.getClosingTime())
                .businessType(request.getBusinessType())
                .build();
        userService.addBusiness(BusinessDto.toBusinessDto(business));

        var jwtToken = jwtService.generateToken(business);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid Username or Password");
        }

        var user = userRepository.findByUsername(request.getUsername());

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
