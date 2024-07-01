package com.fleming99.technician_microservice.core.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CreateTechnicianRequest {

    private String firstName;

    private String lastName;

    private String birthDate;

    private String cpf;

    private String email;

    private String password;
}
