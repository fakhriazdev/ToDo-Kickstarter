package com.kickstarter.kickstarter.service.impl;

import com.kickstarter.kickstarter.constant.ERole;
import com.kickstarter.kickstarter.entity.Role;
import com.kickstarter.kickstarter.repository.RoleRepository;
import com.kickstarter.kickstarter.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Override
    public Role getOrSave(Role role) {
        Optional<Role> optionalRole = roleRepository.findByName(role.getName().toString());
        return optionalRole.orElseGet(()->roleRepository.save(role));

    }

}
