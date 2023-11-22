package com.kickstarter.kickstarter.controller;

import com.kickstarter.kickstarter.dto.request.TaskRequest;
import com.kickstarter.kickstarter.dto.request.UpdateTaskRequest;
import com.kickstarter.kickstarter.dto.response.CommonResponse;
import com.kickstarter.kickstarter.dto.response.RegisterResponse;
import com.kickstarter.kickstarter.dto.response.TaskResponse;
import com.kickstarter.kickstarter.service.TaskService;
import com.kickstarter.kickstarter.service.impl.TaskServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final TaskService taskService;

    @PostMapping("/task")
    public ResponseEntity<?> addTask(@RequestBody TaskRequest request){
        TaskResponse addTaskResponse = taskService.addTask(request);
        CommonResponse<TaskResponse> response = CommonResponse.<TaskResponse>builder()
                .message("successfully create new task")
                .statusCode(HttpStatus.CREATED.value())
                .data(addTaskResponse)
                .build();
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);

    }

    @PutMapping("/task")
    public ResponseEntity<?> updateTask(@RequestBody UpdateTaskRequest request){
        TaskResponse updateTaskResponse = taskService.updateTask(request);
        CommonResponse<TaskResponse> response = CommonResponse.<TaskResponse>builder()
               .message("successfully update task")
               .statusCode(HttpStatus.OK.value())
               .data(updateTaskResponse)
               .build();
        return ResponseEntity
               .status(HttpStatus.OK)
               .body(response);
    }

}
