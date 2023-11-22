package com.kickstarter.kickstarter.service;

import com.kickstarter.kickstarter.dto.request.LoginRequest;
import com.kickstarter.kickstarter.dto.request.RegisterRequest;
import com.kickstarter.kickstarter.dto.response.LoginResponse;
import com.kickstarter.kickstarter.dto.response.RegisterResponse;

public interface AuthService {
    RegisterResponse registerUser (RegisterRequest request);
    LoginResponse login(LoginRequest request);

}
