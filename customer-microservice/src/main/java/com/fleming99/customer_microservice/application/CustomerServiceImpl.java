package com.fleming99.customer_microservice.application;

import com.fleming99.customer_microservice.adapters.AddressRepository;
import com.fleming99.customer_microservice.adapters.CustomerRepository;
import com.fleming99.customer_microservice.core.dto.CreateCustomerRequest;
import com.fleming99.customer_microservice.core.dto.CustomerRequest;
import com.fleming99.customer_microservice.core.dto.CustomerResponse;
import com.fleming99.customer_microservice.core.entities.Address;
import com.fleming99.customer_microservice.core.entities.Customer;
import com.fleming99.customer_microservice.core.exceptions.CouldNotCreateCustomer;
import com.fleming99.customer_microservice.core.exceptions.CouldNotUpdateCustomer;
import com.fleming99.customer_microservice.core.exceptions.CustomerNotFound;
import com.fleming99.customer_microservice.core.usecases.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final AddressRepository addressRepository;

    @Override
    public List<CustomerResponse> getCustomersResponseList() {

        List<Customer> customers = customerRepository.findAll();

        return customers.stream()
                .map(this::mapCustomerToCustomerResponse)
                .toList();
    }

    private CustomerResponse mapCustomerToCustomerResponse(Customer customer) {

        return CustomerResponse.builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .build();
    }

    @Override
    public CustomerResponse getCustomerResponseById(Long id) {

        Optional<Customer> customer = customerRepository.findById(id);

        try {
            return mapCustomerToCustomerResponse(customer.get());
        }catch (Exception e){
            throw new CustomerNotFound(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Customer getCustomerById(Long id) {

        Optional<Customer> customer = customerRepository.findById(id);

        if (customer.isEmpty()){
            throw new RuntimeException("Could not found the customer by id: " + id);
        }

        return customer.get();
    }

    @Override
    @Transactional
    public void createCustomer(CreateCustomerRequest createCustomerRequest) {

        try{
            Customer customer = Customer.builder()
                    .firstName(createCustomerRequest.getFirstName())
                    .lastName(createCustomerRequest.getLastName())
                    .birthDate(LocalDate.parse(createCustomerRequest.getBirthDate()))
                    .regDate(LocalDate.now())
                    .email(createCustomerRequest.getEmail())
                    .password(createCustomerRequest.getPassword())
                    .build();

            Address address = Address.builder()
                    .street(createCustomerRequest.getStreet())
                    .neighborhood(createCustomerRequest.getNeighborhood())
                    .houseNumber(createCustomerRequest.getHouseNumber())
                    .city(createCustomerRequest.getCity())
                    .state(createCustomerRequest.getState())
                    .country(createCustomerRequest.getCountry())
                    .customerId(customer)
                    .build();

            customerRepository.save(customer);
            log.info("Customer {} saved", customer.getId());
            addressRepository.save(address);
            log.info("Address {} saved", address.getId());

        }catch (Exception e){
            throw new CouldNotCreateCustomer(e.getMessage(), e.getCause());
        }
    }

    @Override
    @Transactional
    public void updateCustomer(Long id, CreateCustomerRequest createCustomerRequest) {

        try {
            Customer customer = getCustomerById(id);

            customer.setFirstName(createCustomerRequest.getFirstName());
            customer.setLastName(createCustomerRequest.getLastName());
            customer.setBirthDate(LocalDate.parse(createCustomerRequest.getBirthDate()));
            customer.setEmail(createCustomerRequest.getEmail());
            customer.setPassword(createCustomerRequest.getPassword());

            List<Address> address = addressRepository.findAddressByCustomerId(id);

            for (Address a : address) {
                a.setStreet(createCustomerRequest.getStreet());
                a.setNeighborhood(createCustomerRequest.getNeighborhood());
                a.setHouseNumber(createCustomerRequest.getHouseNumber());
                a.setCity(createCustomerRequest.getCity());
                a.setState(createCustomerRequest.getState());
                a.setCountry(createCustomerRequest.getCountry());
            }

            customerRepository.save(customer);
            log.info("Customer {} updated", customer.getId());

            for (Address a : address) {
                addressRepository.save(a);
                log.info("Address {} updated", a.getId());
            }

        }catch (Exception e){
            throw new CouldNotUpdateCustomer(e.getMessage(), e.getCause());
        }
    }

    @Override
    @Transactional
    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteCustomer(CustomerRequest customerRequest) {
        customerRepository.delete(customerRepository.getCustomerByEmail(customerRequest.getEmail()));
    }
}
