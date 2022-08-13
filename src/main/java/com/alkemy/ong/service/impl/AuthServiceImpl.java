package com.alkemy.ong.service.impl;

import com.alkemy.ong.auth.service.JwtUtils;
import com.alkemy.ong.auth.service.impl.UserDetailsServiceImpl;
import com.alkemy.ong.auth.utility.RoleEnum;
import com.alkemy.ong.models.entity.RoleEntity;
import com.alkemy.ong.models.entity.UserEntity;
import com.alkemy.ong.models.mapper.UserMapper;
import com.alkemy.ong.models.request.AuthRequest;
import com.alkemy.ong.models.request.UserRequest;
import com.alkemy.ong.models.response.AuthResponse;
import com.alkemy.ong.models.response.UserDetailsResponse;
import com.alkemy.ong.models.response.UserResponse;
import com.alkemy.ong.repository.RoleRepository;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.service.AuthService;
import com.alkemy.ong.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtUtils jwtUtils;

    public AuthServiceImpl(UserRepository userRepository, UserMapper userMapper,
                           UserDetailsServiceImpl userDetailsService, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userDetailsService = userDetailsService;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public UserDetailsResponse getPersonalInformation(String token) {
        String email = jwtUtils.extractUsername(token.substring(7));
        UserEntity user = userRepository.findByEmail(email)
           .orElseThrow(() -> new UsernameNotFoundException("User not found with the email: " + email));

        return userMapper.userToUserDetail(user);
    }
}
