package com.hgsplanet.userservice.web;

import com.hgsplanet.userservice.model.AuthenticationRequest;
import com.hgsplanet.userservice.model.AuthenticationResponse;
import com.hgsplanet.userservice.model.BusinessRegisterRequest;
import com.hgsplanet.userservice.model.RegisterRequest;
import com.hgsplanet.userservice.service.AuthenticationService;
import com.hgsplanet.userservice.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auth")
public class AuthenticationRestController {

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody @Valid RegisterRequest request){

        return new ResponseEntity<>(authenticationService.register(request), HttpStatus.OK);
    }

    @PostMapping("/registerBusiness")
    public ResponseEntity<AuthenticationResponse> registerBusiness(@RequestBody @Valid BusinessRegisterRequest request){
        return new ResponseEntity<>(authenticationService.registerBusiness(request), HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return new ResponseEntity<>(authenticationService.authenticate(request), HttpStatus.OK);
    }
}
