package com.kickstarter.kickstarter.service;

import com.kickstarter.kickstarter.entity.Status;

public interface StatusService {
    Status getOrSave(Status status);
}
