package com.kickstarter.kickstarter.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateTaskRequest {
    @NotBlank(message = "credentials is required")
    private String userId;
    @NotBlank(message = "task id required")
    private String id;
    @NotBlank(message = "title is required")
    private String title;
    @NotBlank(message = "status is required")
    private String status;
}
