package com.fleming99.customer_microservice.core.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "customers_list")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "customer_first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "customer_last_name", nullable = false, length = 100)
    private String lastName;

    @Column(name = "customer_birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "customer_reg_date", nullable = false)
    private LocalDate regDate;

    @Column(name = "customer_email", nullable = false, length = 100)
    @Email(message = "Invalid e-mail format.")
    private String email;

    @Column(name = "customer_password", nullable = false, length = 68)
    private String password;

    @OneToMany(mappedBy = "customerId",
            cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Address> addresses;
}
