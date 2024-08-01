package com.fleming99.technician_microservice.core.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class TechnicianResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;
}
