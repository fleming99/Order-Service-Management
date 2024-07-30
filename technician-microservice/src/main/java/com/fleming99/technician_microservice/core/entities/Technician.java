package com.fleming99.technician_microservice.core.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

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
    @Column(name = "technician_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "technician_first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "technician_last_name", nullable = false, length = 100)
    private String lastName;

    @Column(name = "technician_birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "technician_cpf", nullable = false, unique = true, length = 14)
    @CPF(message = "Invalid CPF format.")
    private String cpf;

    @Column(name = "technician_reg_date", nullable = false, updatable = false)
    private LocalDate regDate;

    @Column(name = "technician_email", nullable = false, unique = true, length = 100)
    @Email(message = "Invalid e-mail format.")
    private String email;

    @Column(name = "technician_password", nullable = false, length = 68)
    private String password;
}
