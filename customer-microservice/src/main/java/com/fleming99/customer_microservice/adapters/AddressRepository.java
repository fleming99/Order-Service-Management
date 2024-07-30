package com.fleming99.customer_microservice.adapters;

import com.fleming99.customer_microservice.core.entities.Address;
import com.fleming99.customer_microservice.core.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query(value = "SELECT * FROM customer_address WHERE customer_id = ?1", nativeQuery = true)
    List<Address> findAddressByCustomerId(Long id);
}
