package com.fleming99.technician_microservice.core.dto;

import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CreateTechnicianRequest {

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private String cpf;

    private String rg;

    private LocalDate admissionDate;

    private String email;

}
