package com.kickstarter.kickstarter.service.impl;

import com.kickstarter.kickstarter.entity.Status;
import com.kickstarter.kickstarter.repository.StatusRepository;
import com.kickstarter.kickstarter.service.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService {
    private final StatusRepository statusRepository;
    @Override
    public Status getOrSave(Status status) {
        Optional<Status> optionalStatus = statusRepository.findByName(status.getStatus().toString());
        return optionalStatus.orElseGet(()->statusRepository.save(status));
    }
}
