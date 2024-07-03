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
    @Column(name = "os_id")
    private Long id;

    @Column(name = "os_creation_date")
    private LocalDate creationDate;

    @Column(name = "os_closure_date")
    private LocalDate closureDate;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "technician_id")
    private Long technicianId;

    @Column(name = "job_description")
    private String jobDescription;

    @Column(name = "price")
    private Double price;

    @Column(name = "status")
    private Character status;
}
