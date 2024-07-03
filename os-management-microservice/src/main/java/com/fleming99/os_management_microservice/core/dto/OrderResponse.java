package com.fleming99.os_management_microservice.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class OrderResponse {

    private Long customerId;

    private Long technicianId;

    private String jobDescription;

    private Double price;

    private Character status;

    private LocalDate creationDate;

    private LocalDate closureDate;


}
