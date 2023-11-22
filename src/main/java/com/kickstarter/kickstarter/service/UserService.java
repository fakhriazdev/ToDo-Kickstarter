package com.kickstarter.kickstarter.service;

import com.kickstarter.kickstarter.entity.AppUser;
import com.kickstarter.kickstarter.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User getById(String id);
    AppUser loadUserByUserId(String id);
}
