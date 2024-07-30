package com.fleming99.customer_microservice.adapters;

import com.fleming99.customer_microservice.core.entities.Address;
import com.fleming99.customer_microservice.core.entities.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AddressRepositoryITest {

    @Autowired
    private AddressRepository addressRepository;

    private Customer customer;

    private Address address1;

    private Address address2;

    @BeforeEach
    void setUp() {

        addressRepository.deleteAll();

         customer = Customer.builder()
            .firstName("Rafael")
            .lastName("Fleming")
            .birthDate(LocalDate.of(1999,1,1))
            .regDate(LocalDate.now())
            .email("rafaelfleming@teste.com")
            .password("@Teste123")
            .build();

         address1 = Address.builder()
            .street("Rua das Flores")
            .neighborhood("Bairro das Flores")
            .houseNumber("010101")
            .city("Cidade das Flores")
            .state("Estado das Flores")
            .country("País das Flores")
            .customerId(customer)
            .build();

         address2 = Address.builder()
            .street("Rua das Acácias")
            .neighborhood("Bairro das Acácias")
            .houseNumber("010101")
            .city("Cidade das Acácias")
            .state("Estado das Acácias")
            .country("País das Acácias")
            .customerId(customer)
            .build();
    }

    @Test
    @DisplayName("Save address Repository Test")
    public void givenAddressObject_whenSaveAddress_thenReturnSavedAddress(){

        //given

        //when
        Address address = addressRepository.save(address1);

        //then
        assertThat(address).isNotNull();
        assertThat(address.getId()).isGreaterThan(0);
    }

    @Test
    @DisplayName("Find Address By Customer Id Custom Query Test")
    public void givenAddressObject_whenFindAddressByCustomerId_thenReturnAddressesList() {

        //given
        addressRepository.save(address1);

        //when
        List<Address> address = addressRepository.findAddressByCustomerId(customer.getId());

        //then
        assertThat(address).isNotNull();
    }

    @Test
    @DisplayName("Find all Repository Test")
    public void givenAddressesList_whenFindAll_thenReturnAddressesList(){

        //given
        List<Address> addresses = addressRepository.saveAll(List.of(address1,address2));

        //when
        List<Address> addressListResponse = addressRepository.findAll();

        //then
        assertThat(addressListResponse).isNotNull();
        assertThat(addressListResponse.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Find By Id Repository Test")
    public void givenAddressObject_whenFindById_thenReturnAddressObject(){

        //given
        addressRepository.save(address1);

        //when
        Address address = addressRepository.findById(address1.getId()).get();

        //then
        assertThat(address).isNotNull();
        assertThat(address).isEqualTo(address1);
    }

    @Test
    @DisplayName("Update Address Repository Test")
    public void givenAddressObject_whenUpdateAddress_thenReturnUpdatedAddress(){

        //given
        addressRepository.save(address1);

        //when
        Address addressToUpdate = addressRepository.findById(address1.getId()).get();

        addressToUpdate = Address.builder()
                .street("Rua das Acácias")
                .neighborhood("Bairro das Acácias")
                .houseNumber("010101")
                .city("Cidade das Acácias")
                .state("Estado das Acácias")
                .country("País das Acácias")
                .customerId(customer)
                .build();

        Address updatedAddress = addressRepository.save(addressToUpdate);

        //then
        assertThat(updatedAddress).isNotNull();
        assertThat(updatedAddress.getStreet()).isEqualTo("Rua das Acácias");
        assertThat(updatedAddress.getNeighborhood()).isEqualTo("Bairro das Acácias");
        assertThat(updatedAddress.getHouseNumber()).isEqualTo("010101");
        assertThat(updatedAddress.getCity()).isEqualTo("Cidade das Acácias");
        assertThat(updatedAddress.getState()).isEqualTo("Estado das Acácias");
        assertThat(updatedAddress.getCountry()).isEqualTo("País das Acácias");
    }

    @Test
    @DisplayName("Delete By Id Repository Test")
    public void givenAddressObject_whenDeleteById_thenNothing(){

        //given
        addressRepository.save(address1);

        //when
        addressRepository.deleteById(address1.getId());
        Optional<Address> address = addressRepository.findById(address1.getId());

        //then
        assertThat(address).isEmpty();
    }
}