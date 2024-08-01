package com.fleming99.technician_microservice.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UpdateTechnicianRequest {

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private String cpf;

    private String rg;

    private LocalDate admissionDate;

    private LocalDate resignationDate;

    private String email;

}
