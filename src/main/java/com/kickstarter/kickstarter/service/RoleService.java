package com.kickstarter.kickstarter.service;

import com.kickstarter.kickstarter.entity.Role;

public interface RoleService {
    Role getOrSave(Role role);
}
