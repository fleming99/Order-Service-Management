package com.fleming99.customer_microservice.controllers;

import com.fleming99.customer_microservice.application.CustomerServiceImpl;
import com.fleming99.customer_microservice.core.dto.CreateCustomerRequest;
import com.fleming99.customer_microservice.core.dto.CustomerRequest;
import com.fleming99.customer_microservice.core.dto.CustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerServiceImpl customerService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerResponse> getCustomerList(){
        return customerService.getCustomersResponseList();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerResponse getCustomerById(@PathVariable long id){
        return customerService.getCustomerResponseById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCustomer(@RequestBody CreateCustomerRequest createCustomerRequest){
        customerService.createCustomer(createCustomerRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateCustomer(@PathVariable Long id, @RequestBody CreateCustomerRequest createCustomerRequest){
        customerService.updateCustomer(id, createCustomerRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomerById(@PathVariable Long id){
        customerService.deleteCustomerById(id);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@RequestBody CustomerRequest customerRequest){
        customerService.deleteCustomer(customerRequest);
    }

}
