package com.university.authservice.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.Valid;

public record AuthCreateUserRequest(
        @NotBlank String username,
        @NotBlank String password,
        @Valid AuthCreateRoleRequest roleRequest
) {}
