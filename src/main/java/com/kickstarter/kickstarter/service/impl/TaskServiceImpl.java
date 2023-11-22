package com.kickstarter.kickstarter.service.impl;

import com.kickstarter.kickstarter.constant.ERole;
import com.kickstarter.kickstarter.constant.EStatus;
import com.kickstarter.kickstarter.dto.request.TaskRequest;
import com.kickstarter.kickstarter.dto.request.UpdateTaskRequest;
import com.kickstarter.kickstarter.dto.response.TaskResponse;
import com.kickstarter.kickstarter.entity.Role;
import com.kickstarter.kickstarter.entity.Status;
import com.kickstarter.kickstarter.entity.Task;
import com.kickstarter.kickstarter.entity.User;
import com.kickstarter.kickstarter.repository.TaskRepository;
import com.kickstarter.kickstarter.repository.UserRepository;
import com.kickstarter.kickstarter.service.StatusService;
import com.kickstarter.kickstarter.service.TaskService;
import com.kickstarter.kickstarter.service.UserService;
import com.kickstarter.kickstarter.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final ValidationUtil validationUtil;
    private final UserRepository userRepository;
    private final UserService userService;
    private final StatusService statusService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public TaskResponse addTask(TaskRequest request) {
        validationUtil.validate(request);
        User user = userService.getById(request.getUserId());
        String id = UUID.randomUUID().toString();
        ;

        Status status = statusService.getOrSave(
                Status.builder()
                        .id(id)
                        .status(EStatus.PROGRESS)
                        .build()
        );

        Task task = Task.builder()
                .id(id)
                .title(request.getTitle())
                .status(status)
                .build();
        taskRepository.saveTask(task.getId(), task.getTitle(), task.getStatus().getId(), user.getId());
        return TaskResponse.builder()
                .title(task.getTitle())
                .status(status.getStatus().name())
                .build();
    }
    @Transactional
    @Override
    public TaskResponse updateTask(UpdateTaskRequest request) {
        Task getTask = findByIdOrThrowNotFound(request.getId());

        User user = userService.getById(request.getUserId());
        EStatus eStatus = null;
        if(request.getStatus().equals("DONE")){
            eStatus = EStatus.DONE;
        }else {
            eStatus = EStatus.PROGRESS;
        }

        String id = UUID.randomUUID().toString();
        Status status = statusService.getOrSave(
                Status.builder()
                        .id(id)
                        .status(eStatus)
                        .build()
        );
        taskRepository.updateTask(getTask.getId(), request.getTitle(),status.getId(),getTask.getUser().getId());
        return TaskResponse.builder()
                .title(request.getTitle())
                .status(status.getId())
                .build();
    }


    private Task findByIdOrThrowNotFound(String id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "task not found"));
    }
}
