package com.alkemy.ong.service.impl;

import com.alkemy.ong.auth.service.JwtUtils;
import com.alkemy.ong.auth.utility.RoleEnum;
import com.alkemy.ong.models.entity.RoleEntity;
import com.alkemy.ong.models.entity.UserEntity;
import com.alkemy.ong.models.mapper.UserMapper;
import com.alkemy.ong.models.request.UserRequest;
import com.alkemy.ong.models.response.UserResponse;
import com.alkemy.ong.repository.RoleRepository;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.service.EmailService;
import com.alkemy.ong.service.GenerateTokenService;
import com.alkemy.ong.service.RegisterService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Set;

@Service
public class RegisterImpl implements RegisterService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final EmailService emailService;
    private final GenerateTokenServiceImpl generateTokenService;

    public RegisterImpl(UserRepository userRepository, RoleRepository roleRepository,
                        PasswordEncoder passwordEncoder, UserMapper userMapper,
                        EmailService emailService, GenerateTokenServiceImpl generateTokenService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.emailService = emailService;
        this.generateTokenService = generateTokenService;
    }

    @Override
    public UserResponse register(UserRequest userRequest) throws UsernameNotFoundException, IOException {
        if (userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
            throw new UsernameNotFoundException("User already exists");
        }
        Set<RoleEntity> roles = roleRepository.findByName(RoleEnum.USER.getFullRoleName());
        if (roles.isEmpty()) {
            throw new NullPointerException();
        }
        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        UserEntity userEntity = userMapper.toUserEntity(userRequest, roles);
        userRepository.save(userEntity);
        emailService.switchEmail(userRequest.getEmail(), 1);

        String token = generateTokenService.generateToken(userRequest.getEmail());

        return userMapper.toUserResponse(userEntity, token);
    }
}
