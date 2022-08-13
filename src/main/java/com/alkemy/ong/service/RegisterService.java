package com.alkemy.ong.service;

import com.alkemy.ong.models.request.UserRequest;
import com.alkemy.ong.models.response.UserResponse;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.io.IOException;

public interface RegisterService {

    UserResponse register(UserRequest userRequest) throws UsernameNotFoundException, IOException;
}
