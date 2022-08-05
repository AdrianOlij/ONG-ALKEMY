package com.alkemy.ong.service;

import com.alkemy.ong.models.request.AuthRequest;
import com.alkemy.ong.models.response.AuthResponse;

public interface LoginService {

    AuthResponse login(AuthRequest authRequest);
}
