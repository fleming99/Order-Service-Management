package com.fleming99.customer_microservice.core.dto;

import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class CreateCustomerRequest {

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private String email;

    private String password;

    private String street;

    private String neighborhood;

    private String houseNumber;

    private String city;

    private String state;

    private String country;
}
