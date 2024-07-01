package com.fleming99.customer_microservice.core.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CustomerRequest {

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private String email;

}
