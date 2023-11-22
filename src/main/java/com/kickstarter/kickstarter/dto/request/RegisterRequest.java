package com.kickstarter.kickstarter.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class RegisterRequest {
    @NotBlank(message = "username is required")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "invalid username")
    @Size(min = 6, max = 16, message = "must be greater than 6 character and less than 17 character")
    private String username;
    @NotBlank(message = "password is required")
    @Size(min = 6, message = "must be greater than 6 character")
    private String password;
    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "phonenumber is required")
    @Size(min = 8, max = 13, message = "must be greater than 8 character and less than 13 character")
    @Pattern(regexp = "^[0-9]+$", message = "invalid phone number")
    private String phoneNumber;
}
