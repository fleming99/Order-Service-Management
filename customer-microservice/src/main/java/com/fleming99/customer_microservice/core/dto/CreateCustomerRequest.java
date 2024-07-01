package com.fleming99.customer_microservice.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CreateCustomerRequest {

    private String firstName;

    private String lastName;

    private String birthDate;

    private String email;

    private String password;

    private String street;

    private String neighborhood;

    private String houseNumber;

    private String city;

    private String state;

    private String country;
}
