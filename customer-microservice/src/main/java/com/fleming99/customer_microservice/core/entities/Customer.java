package com.fleming99.customer_microservice.core.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @Column(name = "customer_id")
    private Long id;

    @Column(name = "customer_first_name")
    private String firstName;

    @Column(name = "customer_last_name")
    private String lastName;

    @Column(name = "customer_birth_date")
    private LocalDate birthDate;

    @Column(name = "customer_reg_date")
    private LocalDate regDate;

    @Column(name = "customer_email")
    private String email;

    @Column(name = "customer_password")
    private String password;

    @OneToMany(mappedBy = "customerId",
            cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Address> addresses;
}
