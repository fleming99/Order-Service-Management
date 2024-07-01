package com.fleming99.customer_microservice.core.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class CustomerResponse {

    private Long id;

    private String firstName;

    private String lastName;
}
