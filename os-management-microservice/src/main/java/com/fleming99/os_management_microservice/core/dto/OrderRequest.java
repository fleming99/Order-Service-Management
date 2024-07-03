package com.fleming99.os_management_microservice.core.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class OrderRequest {

    private Long customerId;

    private Long technicianId;

    private String jobDescription;

    private Double price;

    private Character status;
}
