package com.fleming99.customer_microservice.adapters;

import com.fleming99.customer_microservice.core.entities.Address;
import com.fleming99.customer_microservice.core.entities.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class CustomerRepositoryTest{

    @Autowired
    private CustomerRepository customerRepository;

    private Customer customer1;

    private Customer customer2;

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
        customers.add(customer1);
        customers.add(customer2);
    }

    // JUnit test for save customer operation
    @Test
    @DisplayName("Save Customer Test")
    public void givenCustomerObject_whenSave_ThenReturnSavedCustomer() {

        //given - precondition or setup

        //when - action or behaviour that we are going to test
        Customer savedCustomer = customerRepository.save(customer1);

        //then - verify the output
        assertThat(savedCustomer).isNotNull();
        assertThat(savedCustomer.getId()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Find All Customers Test")
    public void givenCustomersList_whenFindAll_thenReturnCustomersList(){

        //given
        customerRepository.saveAll(customers);

        //when
        List<Customer> customersList = customerRepository.findAll();

        //then
        assertThat(customersList).isNotNull();
        assertThat(customersList.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Find by Id Customer Test")
    public void givenCustomerObject_whenFindById_thenReturnCustomerObject(){

        //given
        customerRepository.save(customer1);

        //when
        Customer savedCustomer = customerRepository.findById(customer1.getId()).get();

        //then
        assertThat(savedCustomer).isNotNull();
        assertThat(savedCustomer.getId()).isEqualTo(customer1.getId());
    }

    @Test
    @DisplayName("Find Customer By Email Test")
    public void givenCustomerEmail_whenFindByEmail_thenReturnCustomerObject(){

        //given
        customerRepository.save(customer1);

        //when
        Customer savedCustomer = customerRepository.findByEmail(customer1.getEmail()).get();

        //then
        assertThat(savedCustomer).isNotNull();
        assertThat(savedCustomer).isEqualTo(customer1);
    }

    @Test
    @DisplayName("Update Customer Test")
    public void givenCustomerObject_whenUpdateCustomer_thenReturnUpdatedCustomerObject(){

        //given
        customerRepository.save(customer1);

        //when
        Customer customerToUpdate = customerRepository.findByEmail(customer1.getEmail()).get();

        customerToUpdate.setFirstName("John");
        customerToUpdate.setLastName("Cena");
        customerToUpdate.setBirthDate(LocalDate.of(2000,1,1));
        customerToUpdate.setEmail("johncenateste@gmail.com");
        customerToUpdate.setPassword("@Teste123");

        Customer updatedCustomer = customerRepository.save(customerToUpdate);

        //then
        assertThat(updatedCustomer).isNotNull();
        assertThat(updatedCustomer.getFirstName()).isEqualTo("John");
        assertThat(updatedCustomer.getLastName()).isEqualTo("Cena");
        assertThat(updatedCustomer.getBirthDate()).isEqualTo(LocalDate.of(2000,1,1));
        assertThat(updatedCustomer.getEmail()).isEqualTo("johncenateste@gmail.com");
        assertThat(updatedCustomer.getPassword()).isEqualTo("@Teste123");
    }

    @Test
    @DisplayName("Delete Customer Test")
    public void givenCustomerObject_whenDelete_thenRemoveCustomer(){

        //given
        customerRepository.save(customer1);

        //when
        customerRepository.delete(customer1);
        Optional<Customer> customerDB = customerRepository.findById(customer1.getId());

        //then
        assertThat(customerDB).isEmpty();
    }

    @Test
    @DisplayName("Delete Customer By Id Test")
    public void givenCustomerId_whenDeleteById_thenRemoveCustomer(){

        //given
        customerRepository.save(customer1);

        //when
        customerRepository.deleteById(customer1.getId());
        Optional<Customer> customerDB = customerRepository.findById(customer1.getId());

        //then
        assertThat(customerDB).isEmpty();
    }
}