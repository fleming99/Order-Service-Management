package com.fleming99.customer_microservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fleming99.customer_microservice.application.CustomerServiceImpl;
import com.fleming99.customer_microservice.core.dto.CreateCustomerRequest;
import com.fleming99.customer_microservice.core.dto.CustomerRequest;
import com.fleming99.customer_microservice.core.dto.CustomerResponse;
import com.fleming99.customer_microservice.core.entities.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerServiceImpl customerService;

    @Autowired
    private ObjectMapper objectMapper;

    private Customer customer;

    private CreateCustomerRequest createCustomerRequest;

    private CustomerRequest customerRequest;

    private CustomerResponse customerResponse;

    @BeforeEach
    void setUp() {

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
    @DisplayName("Create Customer Endpoint Test")
    public void givenCustomerObject_whenCreateCustomer_thenReturnSavedCustomer() throws Exception {

        //given
        willDoNothing().given(customerService).createCustomer(createCustomerRequest);

        //when
        ResultActions response = mockMvc.perform(post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createCustomerRequest)));

        //then
        response.andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    @DisplayName("Update Customer Endpoint Test")
    public void givenCustomerObject_whenUpdateCustomer_thenReturnUpdatedCustomer() throws Exception {

        //given
        willDoNothing().given(customerService).updateCustomer(1L, createCustomerRequest);

        //when
        ResultActions response = mockMvc.perform(put("/customers/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createCustomerRequest)));

        //then
        response.andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("Get All Endpoint Test")
    public void givenCustomerResponseList_whenGetAllCustomerResponseList_thenReturnCustomerResponseList() throws Exception {

        //given
        List<CustomerResponse> customers = new ArrayList<>(List.of(customerResponse));
        given(customerService.getCustomersResponseList()).willReturn(customers);

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
        given(customerService.getCustomerResponseById(1L)).willReturn(customerResponse);

        //when
        ResultActions resultActions = mockMvc.perform(get("/customers/{id}", 1L));

        //then
        resultActions.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is(customerResponse.getFirstName())))
                .andExpect(jsonPath("$.lastName", is(customerResponse.getLastName())));
    }

    @Test
    @DisplayName("Delete By Id Endpoint Test")
    public void givenCustomerId_whenDeleteById_thenNothing() throws Exception {

        //given
        willDoNothing().given(customerService).deleteCustomerById(1L);

        //when
        ResultActions resultActions = mockMvc.perform(delete("/customers/{id}", 1L));

        //then
        resultActions.andExpect(status().isOk());
    }

    @Test
    @DisplayName("Delete By Customer Request Endpoint Test")
    public void givenCustomerRequest_whenDeleteById_thenNothing() throws Exception {

        //given
        willDoNothing().given(customerService).deleteCustomer(customerRequest);

        //when
        ResultActions resultActions = mockMvc.perform(delete("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerRequest)));

        //then
        resultActions.andExpect(status().isOk());
    }

}