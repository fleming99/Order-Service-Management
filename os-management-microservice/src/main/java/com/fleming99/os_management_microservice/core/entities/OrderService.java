package com.fleming99.os_management_microservice.core.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "os_list")
public class OrderService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "os_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "os_creation_date", nullable = false, updatable = false)
    private LocalDate creationDate;

    @Column(name = "os_closure_date", nullable = false, updatable = false)
    private LocalDate closureDate;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "technician_id", nullable = false)
    private Long technicianId;

    @Column(name = "job_description", nullable = false)
    private String jobDescription;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "status", nullable = false)
    private Character status;
}
