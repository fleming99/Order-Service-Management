package com.fleming99.technician_microservice.core.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "technicians_list")
public class Technician {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "technician_id")
    private Long id;

    @Column(name = "technician_first_name")
    private String firstName;

    @Column(name = "technician_last_name")
    private String lastName;

    @Column(name = "technician_birth_date")
    private LocalDate birthDate;

    @Column(name = "technician_cpf")
    private String cpf;

    @Column(name = "technician_reg_date")
    private LocalDate regDate;

    @Column(name = "technician_email")
    private String email;

    @Column(name = "technician_password")
    private String password;
}
