package com.fleming99.customer_microservice.adapters;

import com.fleming99.customer_microservice.container_config.TestContainersConfig;
import com.fleming99.customer_microservice.core.entities.Address;
import com.fleming99.customer_microservice.core.entities.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@RequiredArgsConstructor
@ActiveProfiles("test")
@Slf4j
class AddressRepositoryTest extends TestContainersConfig{

    @Autowired
    AddressRepository addressRepository;

    @Test
    @Rollback
    void findAddressByCustomerIdReturnsAddress() {

        Customer customer = Customer.builder()
                .firstName("Teste")
                .lastName("Teste")
                .birthDate(LocalDate.of(1999,1,1))
                .regDate(LocalDate.now())
                .email("teste@teste.com")
                .password("Teste")
                .build();

        Address address = Address.builder()
                .street("Teste")
                .neighborhood("Teste")
                .houseNumber("Teste")
                .city("Teste")
                .state("Teste")
                .country("Teste")
                .customerId(customer)
                .build();

        addressRepository.save(address);

        assertNotNull(addressRepository.findAddressByCustomerId(customer.getId()));

        log.info(String.format("Return: %s", addressRepository.findAddressByCustomerId(customer.getId())));
    }

}