package com.fleming99.authentication_microservice.core.dto;

import com.fleming99.authentication_microservice.core.entities.UserRole;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class SignupRequest {

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private UserRole userRole;
}
