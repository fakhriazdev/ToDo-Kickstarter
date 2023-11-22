package com.kickstarter.kickstarter.service;

import com.kickstarter.kickstarter.dto.request.TaskRequest;
import com.kickstarter.kickstarter.dto.request.UpdateTaskRequest;
import com.kickstarter.kickstarter.dto.response.TaskResponse;

public interface TaskService {


    TaskResponse addTask(TaskRequest request);

    TaskResponse updateTask(UpdateTaskRequest request);
}
