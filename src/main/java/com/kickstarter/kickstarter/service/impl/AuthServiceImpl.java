package com.kickstarter.kickstarter.service.impl;

import com.kickstarter.kickstarter.constant.ERole;
import com.kickstarter.kickstarter.dto.request.LoginRequest;
import com.kickstarter.kickstarter.dto.request.RegisterRequest;
import com.kickstarter.kickstarter.dto.response.LoginResponse;
import com.kickstarter.kickstarter.dto.response.RegisterResponse;
import com.kickstarter.kickstarter.entity.AppUser;
import com.kickstarter.kickstarter.entity.Role;
import com.kickstarter.kickstarter.entity.User;
import com.kickstarter.kickstarter.entity.UserCredential;
import com.kickstarter.kickstarter.repository.UserCredentialRepository;
import com.kickstarter.kickstarter.repository.UserRepository;
import com.kickstarter.kickstarter.security.JwtUtil;
import com.kickstarter.kickstarter.service.AuthService;
import com.kickstarter.kickstarter.service.RoleService;
import com.kickstarter.kickstarter.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service

public class AuthServiceImpl implements AuthService {
    private final JwtUtil jwtUtil;
    private final UserCredentialRepository userCredentialRepository;
    private final ValidationUtil validationUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private final RoleService roleService;

@Autowired
    public AuthServiceImpl(JwtUtil jwtUtil, UserCredentialRepository userCredentialRepository, ValidationUtil validationUtil, UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, RoleService roleService) {
    this.jwtUtil = jwtUtil;
    this.userCredentialRepository = userCredentialRepository;
        this.validationUtil = validationUtil;
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.authenticationManager = authenticationManager;
    this.roleService = roleService;
}

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RegisterResponse registerUser(RegisterRequest request) {
        try{
            validationUtil.validate(request);
            String id = UUID.randomUUID().toString();

            Role role = roleService.getOrSave(
                    Role.builder()
                            .id(id)
                            .name(ERole.ROLE_USER)
                            .build()
            );

            UserCredential userCredential = UserCredential.builder()
                    .id(id)
                    .username(request.getUsername().toLowerCase())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .role(role)
                    .build();

            userCredentialRepository.saveUserCredential(userCredential.getId(),userCredential.getUsername(),userCredential.getPassword(),userCredential.getRole().getId());

            User user = User.builder()
                    .id(id)
                    .name(request.getName())
                    .phoneNumber(request.getPhoneNumber())
                    .userCredential(userCredential)
                    .build();

            userRepository.saveUser(id,request.getName(), request.getPhoneNumber(),user.getUserCredential().getId());

            return RegisterResponse.builder()
                    .username(userCredential.getUsername())
                    .name(user.getName())
                    .build();

        }catch (DataIntegrityViolationException e){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "user already exist");
        }
    }

    @Override
    public LoginResponse login(LoginRequest request) {
    validationUtil.validate(request);
    Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
            request.getUsername().toLowerCase(),
            request.getPassword()
    ));
    SecurityContextHolder.getContext().setAuthentication(authenticate);

    AppUser appUser = (AppUser) authenticate.getPrincipal();
    String token = jwtUtil.generateToken(appUser);
    return LoginResponse.builder()
            .token(token)
            .username(appUser.getUsername())
            .role(appUser.getRole().name())
            .build();
    }
}
