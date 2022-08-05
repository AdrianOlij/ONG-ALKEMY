package com.alkemy.ong.service.impl;

import com.alkemy.ong.auth.service.JwtUtils;
import com.alkemy.ong.models.request.AuthRequest;
import com.alkemy.ong.models.response.AuthResponse;
import com.alkemy.ong.service.LoginService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{

    private final AuthenticationManager authenticationManager;
    private final GenerateTokenServiceImpl generateTokenService;

    public LoginServiceImpl(AuthenticationManager authenticationManager, GenerateTokenServiceImpl generateTokenService) {
        this.authenticationManager = authenticationManager;
        this.generateTokenService = generateTokenService;
    }

    @Override
    public AuthResponse login(AuthRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
            String token = generateTokenService.generateToken(authRequest.getEmail());
            return AuthResponse.builder()
                    .email(authRequest.getEmail())
                    .token(token)
                    .build();
        } catch (Exception e) {
            return AuthResponse.builder().ok(false).build();
        }
    }
}
