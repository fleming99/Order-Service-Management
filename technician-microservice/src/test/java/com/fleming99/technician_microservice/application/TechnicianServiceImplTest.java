package com.fleming99.technician_microservice.application;

import com.fleming99.technician_microservice.adapters.TechnicianRepository;
import com.fleming99.technician_microservice.core.entities.Technician;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TechnicianServiceImplTest {

    @Mock
    private TechnicianRepository technicianRepository;

    @InjectMocks
    private TechnicianServiceImpl technicianService;

    private Technician technician1;

    private Technician technician2;

    @BeforeEach
    void setUp() {

        technician1 = Technician.builder()
                .firstName("Rafael")
                .lastName("Fleming")
                .birthDate(LocalDate.parse("1999-01-01"))
                .cpf("000.000.000-01")
                .regDate(LocalDate.now())
                .email("rfleming1235@gmail.com")
                .build();

        technician2 = Technician.builder()
                .firstName("John")
                .lastName("Cena")
                .birthDate(LocalDate.parse("1980-01-01"))
                .cpf("000.000.000-02")
                .regDate(LocalDate.now())
                .email("johncena@gmail.com")
                .build();
    }

    @Test
    public void givenTechnicianObject_whenSaveTechnician_then(){

        //given
        BDDMockito.given(technicianRepository.save(technician1)).willReturn(technician1);

        //when


        //then
    }

}