package com.fleming99.customer_microservice.adapters;

import com.fleming99.customer_microservice.core.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "SELECT * FROM customers_list WHERE customer_email = ?1", nativeQuery = true)
    Customer getCustomerByEmail(String email);
}
