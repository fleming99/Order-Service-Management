package com.fleming99.technician_microservice.adapters;

import com.fleming99.technician_microservice.utils.CPFGenerator;
import com.fleming99.technician_microservice.core.entities.Technician;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class TechnicianRepositoryTest {

    @Autowired
    private TechnicianRepository technicianRepository;

    private Technician technician1;

    private Technician technician2;

    @BeforeEach
    void setUp() {

        technician1 = Technician.builder()
                .firstName("Rafael")
                .lastName("Fleming")
                .birthDate(LocalDate.of(1999,1,1))
                .cpf(CPFGenerator.generate())
                .rg("00.000.000-1")
                .regDate(LocalDate.now())
                .admissionDate(LocalDate.of(2024,7,7))
                .status('A')
                .email("rfleming1235@gmail.com")
                .build();

        technician2 = Technician.builder()
                .firstName("John")
                .lastName("Cena")
                .birthDate(LocalDate.of(1999,1,1))
                .cpf(CPFGenerator.generate())
                .rg("00.000.000-2")
                .regDate(LocalDate.now())
                .admissionDate(LocalDate.of(2024,7,1))
                .status('A')
                .email("johncena@gmail.com")
                .build();
    }

    @Test
    @DisplayName("Save Technician Repository Test")
    public void givenTechnicianObject_whenSaveTechnician_thenReturnSavedTechnician(){

        //given

        //when
        System.out.println(technician1.toString());
        Technician savedTechnician = technicianRepository.save(technician1);

        //then
        assertThat(savedTechnician).isNotNull();
        assertThat(savedTechnician).isEqualTo(technician1);
    }

    @Test
    @DisplayName("Find All Technicians Repository Test")
    public void givenTechnicianList_whenFindAll_thenReturnTechnicianList(){

        //given
        technicianRepository.save(technician1);
        technicianRepository.save(technician2);

        //when
        List<Technician> savedTechnicians = technicianRepository.findAll();

        //then
        assertThat(savedTechnicians).isNotNull();
        assertThat(savedTechnicians.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Find Technician by Id Repository Test")
    public void givenTechnicianId_whenFindById_thenReturnSavedTechnician(){

        //given
        technicianRepository.save(technician1);

        //when
        Technician savedTechnician = technicianRepository.findById(technician1.getId()).get();

        //then
        assertThat(savedTechnician).isNotNull();
        assertThat(savedTechnician.getId()).isEqualTo(technician1.getId());
    }

    @Test
    @DisplayName("Find Technician By Email Repository Test")
    public void givenTechnicianEmail_whenFindByEmail_thenReturnSavedTechnician(){

        //given
        technicianRepository.save(technician1);

        //when
        Technician savedTechnician = technicianRepository.getTechnicianByEmail(technician1.getEmail());

        //then
        assertThat(savedTechnician).isNotNull();
        assertThat(savedTechnician.getEmail()).isEqualTo(technician1.getEmail());
    }

    @Test
    public void givenTechnicianId_whenUpdateTechnician_thenReturnUpdatedTechnician(){

        //given
        technicianRepository.save(technician1);

        Technician technicianToUpdate = technicianRepository.findById(technician1.getId()).get();

        technicianToUpdate = Technician.builder()
                .firstName("Josh")
                .lastName("Ramed")
                .birthDate(LocalDate.parse("1998-01-01"))
                .cpf(CPFGenerator.generate())
                .rg("00.000.000-3")
                .regDate(LocalDate.now())
                .admissionDate(LocalDate.of(2024,7,5))
                .resignationDate(LocalDate.of(2024,7,15))
                .email("josh@gmail.com")
                .build();

        //when
        Technician updatedTechnician = technicianRepository.save(technicianToUpdate);

        //then
        assertThat(updatedTechnician).isNotNull();
        assertThat(updatedTechnician.getFirstName()).isNotEqualTo(technician1.getFirstName());
        assertThat(updatedTechnician.getLastName()).isNotEqualTo(technician1.getLastName());
        assertThat(updatedTechnician.getCpf()).isNotEqualTo(technician1.getCpf());
        assertThat(updatedTechnician.getEmail()).isNotEqualTo(technician1.getEmail());
    }

    @Test
    @DisplayName("Delete Technician by Id Repository Test")
    public void givenTechnicianId_whenDeleteById_thenDeleteTechnician(){

        //given
        technicianRepository.save(technician1);

        //when
        technicianRepository.deleteById(technician1.getId());
        Optional<Technician> savedTechnician = technicianRepository.findById(technician1.getId());

        //then
        assertThat(savedTechnician).isEmpty();
    }
}