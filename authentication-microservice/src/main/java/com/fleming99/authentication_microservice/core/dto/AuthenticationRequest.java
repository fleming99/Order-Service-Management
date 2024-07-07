package com.fleming99.authentication_microservice.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class AuthenticationRequest {

    private String email;

    private String password;
}
