package com.fleming99.customer_microservice.core.usecases;

import com.fleming99.customer_microservice.core.dto.CreateCustomerRequest;
import com.fleming99.customer_microservice.core.dto.CustomerRequest;
import com.fleming99.customer_microservice.core.dto.CustomerResponse;
import com.fleming99.customer_microservice.core.entities.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    List<Customer> getCustomersList();

    List<CustomerResponse> getCustomersResponseList();

    CustomerResponse getCustomerResponseById(Long id);

    Customer getCustomerById(Long id);

    void createCustomer(CreateCustomerRequest createCustomerRequest);

    void updateCustomer(Long id, CreateCustomerRequest createCustomerRequest);

    void deleteCustomerById(Long id);

    void deleteCustomer(CustomerRequest customerRequest);
}
