package com.kickstarter.kickstarter.controller;

import com.kickstarter.kickstarter.dto.request.LoginRequest;
import com.kickstarter.kickstarter.dto.request.RegisterRequest;
import com.kickstarter.kickstarter.dto.response.CommonResponse;
import com.kickstarter.kickstarter.dto.response.LoginResponse;
import com.kickstarter.kickstarter.dto.response.RegisterResponse;
import com.kickstarter.kickstarter.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register/user")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request){
        RegisterResponse registerResponse = authService.registerUser(request);
        CommonResponse<RegisterResponse> response = CommonResponse.<RegisterResponse>builder()
                .message("successfully register new user")
                .statusCode(HttpStatus.CREATED.value())
                .data(registerResponse)
                .build();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request){
        LoginResponse login = authService.login(request);
        CommonResponse<LoginResponse> response = CommonResponse.<LoginResponse>builder()
                .message("successfully login")
                .statusCode(HttpStatus.OK.value())
                .data(login)
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
