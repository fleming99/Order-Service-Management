package com.fleming99.customer_microservice.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fleming99.customer_microservice.adapters.AddressRepository;
import com.fleming99.customer_microservice.adapters.CustomerRepository;
import com.fleming99.customer_microservice.core.dto.CreateCustomerRequest;
import com.fleming99.customer_microservice.core.dto.CustomerRequest;
import com.fleming99.customer_microservice.core.dto.CustomerResponse;
import com.fleming99.customer_microservice.core.entities.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CustomerControllerITests extends mysqlContainer{

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Customer customer;

    private CreateCustomerRequest createCustomerRequest;

    private CustomerRequest customerRequest;

    private CustomerResponse customerResponse;

    @BeforeEach
    void setUp(){
        customerRepository.deleteAll();
        addressRepository.deleteAll();

        customer = Customer.builder()
                .firstName("Rafael")
                .lastName("Fleming")
                .birthDate(LocalDate.of(1999, 1, 1))
                .regDate(LocalDate.now())
                .email("rafaelflemingteste@gmail.com")
                .password("Teste123@")
                .build();

        createCustomerRequest = CreateCustomerRequest.builder()
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

        customerRequest = CustomerRequest.builder()
                .firstName("Rafael")
                .lastName("Fleming")
                .birthDate(LocalDate.parse("1999-01-01"))
                .email("rafaelflemingteste@gmail.com")
                .build();

        customerResponse = CustomerResponse.builder()
                .id(1L)
                .firstName("Rafael")
                .lastName("Fleming")
                .build();
    }

    @Test
    @DisplayName("Create Customer Endpoint Integration Test")
    public void givenCustomerObject_whenCreateCustomer_thenReturnSavedCustomer() throws Exception {

        //given

        //when
        ResultActions response = mockMvc.perform(post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createCustomerRequest)));

        //then
        response.andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    @DisplayName("Get All Endpoint Test")
    public void givenCustomerResponseList_whenGetAllCustomerResponseList_thenReturnCustomerResponseList() throws Exception {

        //given
        customerRepository.save(customer);
        List<CustomerResponse> customers = new ArrayList<>(List.of(customerResponse));

        //when
        ResultActions response = mockMvc.perform(get("/customers"));

        //then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(customers.size())));
    }

    @Test
    @DisplayName("Get By Id Endpoint Test")
    public void givenCustomerId_whenGetCustomerById_thenReturnCustomerResponseObject() throws Exception {

        //given
        customerRepository.save(customer);

        //when
        ResultActions resultActions = mockMvc.perform(get("/customers/{id}", customer.getId()));

        //then
        resultActions.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id", is(customer.getId().intValue())))
                .andExpect(jsonPath("$.firstName", is(customer.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(customer.getLastName())));
    }

    @Test
    @DisplayName("Update Customer Endpoint Test")
    public void givenCustomerObject_whenUpdateCustomer_thenReturnUpdatedCustomer() throws Exception {

        //given
        customerRepository.save(customer);

        //when
        ResultActions response = mockMvc.perform(put("/customers/{id}", customer.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createCustomerRequest)));

        //then
        response.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("Delete By Id Endpoint Test")
    public void givenCustomerId_whenDeleteById_thenNothing() throws Exception {

        //given
        customerRepository.save(customer);

        //when
        ResultActions resultActions = mockMvc.perform(delete("/customers/{id}", customer.getId()));

        //then
        resultActions.andExpect(status().isOk());
    }

    @Test
    @DisplayName("Delete By Customer Request Endpoint Test")
    public void givenCustomerRequest_whenDeleteById_thenNothing() throws Exception {

        //given
        customerRepository.save(customer);

        //when
        ResultActions resultActions = mockMvc.perform(delete("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerRequest)));

        //then
        resultActions.andExpect(status().isOk());
    }

}
