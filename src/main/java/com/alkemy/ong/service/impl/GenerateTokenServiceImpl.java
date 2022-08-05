package com.alkemy.ong.service.impl;

import com.alkemy.ong.auth.service.JwtUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class GenerateTokenServiceImpl {

    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;

    public GenerateTokenServiceImpl(JwtUtils jwtUtils, UserDetailsService userDetailsService) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    protected String generateToken(String userRequest) {
        return jwtUtils.generateToken(userDetailsService.loadUserByUsername(userRequest));
    }
}
