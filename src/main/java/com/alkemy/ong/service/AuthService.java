package com.alkemy.ong.service;

import com.alkemy.ong.models.request.AuthRequest;
import com.alkemy.ong.models.request.UserRequest;
import com.alkemy.ong.models.response.AuthResponse;
import com.alkemy.ong.models.response.UserDetailsResponse;
import com.alkemy.ong.models.response.UserResponse;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.io.IOException;

public interface AuthService {
    UserDetailsResponse getPersonalInformation(String token);
}
