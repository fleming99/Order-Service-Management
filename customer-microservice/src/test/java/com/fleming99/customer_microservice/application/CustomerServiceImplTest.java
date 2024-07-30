package com.fleming99.customer_microservice.application;

import com.fleming99.customer_microservice.adapters.AddressRepository;
import com.fleming99.customer_microservice.adapters.CustomerRepository;
import com.fleming99.customer_microservice.core.dto.CreateCustomerRequest;
import com.fleming99.customer_microservice.core.dto.CustomerResponse;
import com.fleming99.customer_microservice.core.entities.Address;
import com.fleming99.customer_microservice.core.entities.Customer;
import com.fleming99.customer_microservice.core.exceptions.CouldNotCreateCustomer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest{

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private Customer customer1;

    private Customer customer2;

    private Address address;

    private CreateCustomerRequest customerRequest;

    private CustomerResponse customerResponse;

    private List<Customer> customers = new ArrayList<>();

    @BeforeEach
    public void setup(){

        customer1 = Customer.builder()
                .firstName("Rafael")
                .lastName("Fleming")
                .birthDate(LocalDate.of(1999,1,1))
                .regDate(LocalDate.now())
                .email("rafaelflemingteste@gmail.com")
                .password("Teste123@")
                .build();

        customer2 = Customer.builder()
                .firstName("John")
                .lastName("Cena")
                .birthDate(LocalDate.of(1999,3,2))
                .regDate(LocalDate.now())
                .email("johncenateste@gmail.com")
                .password("Teste123@")
                .build();

        address = Address.builder()
                .street("Rua das Flores")
                .neighborhood("Bairro das Flores")
                .houseNumber("010101")
                .city("Cidade das Flores")
                .state("Estado das Flores")
                .country("País das Flores")
                .customerId(customer1)
                .build();

        customerRequest = CreateCustomerRequest.builder()
                .firstName("Rafael")
                .lastName("Fleming")
                .birthDate(LocalDate.parse("1999-01-01"))
                .email("rafaelflemingteste@gmail.com")
                .password("Teste123@")
                .street("Rua das Flores")
                .neighborhood("Bairro das Flores")
                .houseNumber("010101")
                .city("Cidade das Flores")
                .state("Estado das Flores")
                .country("País das Flores")
                .build();

        customerResponse = CustomerResponse.builder()
                .id(1L)
                .firstName("Rafael")
                .lastName("Fleming")
                .build();

        customers.add(customer1);
        customers.add(customer2);
    }

    @Test
    @DisplayName("Save Customer Service Test")
    public void givenCreateCustomerRequest_whenSaveCustomer_thenReturnCustomerObject(){

        //given
        given(customerRepository.findByEmail(customerRequest.getEmail())).willReturn(Optional.empty());
        given(customerRepository.save(customer1)).willReturn(customer1);
        given(addressRepository.save(address)).willReturn(address);

        //when
        customerService.createCustomer(customerRequest);

        //then
        verify(customerRepository, times(1)).save(customer1);
    }

    @Test
    @DisplayName("Save Customer Service Exception Test")
    public void givenExistingEmail_whenSaveCustomer_thenThrowsException(){

        //given
        given(customerRepository.findByEmail(customerRequest.getEmail())).willReturn(Optional.of(customer1));

        //when
        assertThrows(CouldNotCreateCustomer.class, () -> customerService.createCustomer(customerRequest));

        //then
        verify(customerRepository, never()).save(customer1);
    }

    @Test
    @DisplayName("Get Customers List Service Test")
    public void givenCustomersList_whenGetCustomersList_thenReturnCustomerList(){

        //given
        given(customerRepository.findAll()).willReturn(List.of(customer1, customer2));

        //when
        List<Customer> customerList = customerService.getCustomersList();

        //then
        assertThat(customerList).isNotNull();
        assertThat(customerList.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Get Empty Customers List Service Test")
    public void givenEmptyCustomersList_whenGetCustomersList_thenReturnEmptyCustomerList(){

        //given
        given(customerRepository.findAll()).willReturn(Collections.emptyList());

        //when
        List<Customer> customerList = customerService.getCustomersList();

        //then
        assertThat(customerList).isEmpty();
    }

    @Test
    @DisplayName("Get Customer By Id Service Test")
    public void givenCustomerId_whenGetCustomerById_thenReturnCustomerObject(){

        //given
        given(customerRepository.findById(customer1.getId())).willReturn(Optional.of(customer1));

        //when
        Customer customer = customerService.getCustomerById(customer1.getId());

        //then
        assertThat(customer).isNotNull();
    }

    @Test
    @DisplayName("Update Customer Service Test")
    public void givenCustomerObject_whenUpdateCustomer_thenReturnUpdatedCustomer(){

        //given
        given(customerRepository.findById(customer2.getId())).willReturn(Optional.of(customer2));
        given(customerRepository.save(customer2)).willReturn(customer2);
        given(addressRepository.findAddressByCustomerId(customer2.getId())).willReturn(List.of(address));
        given(addressRepository.save(address)).willReturn(address);

        //when
        customerService.updateCustomer(customer2.getId(),customerRequest);

        //then
        verify(customerRepository, times(1)).save(customer2);
    }

    @Test
    @DisplayName("Delete by Id Customer Service Test")
    public void givenCustomerId_whenDeleteId_thenReturnDeletedCustomer(){

        //given
        willDoNothing().given(customerRepository).deleteById(customer1.getId());

        //when
        customerService.deleteCustomerById(customer1.getId());

        //then
        verify(customerRepository, times(1)).deleteById(customer1.getId());
    }
}