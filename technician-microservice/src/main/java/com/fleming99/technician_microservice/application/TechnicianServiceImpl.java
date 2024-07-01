package com.fleming99.technician_microservice.application;

import com.fleming99.technician_microservice.adapters.TechnicianRepository;
import com.fleming99.technician_microservice.core.dto.CreateTechnicianRequest;
import com.fleming99.technician_microservice.core.dto.TechnicianRequest;
import com.fleming99.technician_microservice.core.dto.TechnicianResponse;
import com.fleming99.technician_microservice.core.entities.Technician;
import com.fleming99.technician_microservice.core.usecases.TechnicianUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TechnicianServiceImpl implements TechnicianUseCase {

    @Autowired
    private TechnicianRepository technicianRepository;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public List<TechnicianResponse> getTechniciansResponseList() {

        List<Technician> technicians = technicianRepository.findAll();

        try {
            return technicians
                    .stream()
                    .map(this::mapTechnicianToTechnicianResponse)
                    .toList();
        }catch (NullPointerException e){
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    private TechnicianResponse mapTechnicianToTechnicianResponse(Technician technician) {

        return TechnicianResponse.builder()
                .firstName(technician.getFirstName())
                .lastName(technician.getLastName())
                .cpf(technician.getCpf())
                .email(technician.getEmail())
                .build();
    }

    @Override
    public TechnicianResponse getTechnicianResponseById(Long id) {

        Optional<Technician> technician = technicianRepository.findById(id);

        if (technician.isEmpty()){
            throw new RuntimeException("Did not found the technician by id: " + id);
        }

        return mapTechnicianToTechnicianResponse(technician.get());
    }

    @Override
    public Technician getTechnicianById(Long id) {

        Optional<Technician> technician = technicianRepository.findById(id);

        if (technician.isEmpty()){
            throw new RuntimeException("Did not found the technician by id: " + id);
        }

        return technician.get();
    }

    @Override
    @Transactional
    public void createTechnician(CreateTechnicianRequest technicianRequest) {

        try {
            Technician technician = Technician.builder()
                    .firstName(technicianRequest.getFirstName())
                    .lastName(technicianRequest.getLastName())
                    .birthDate(LocalDate.parse(technicianRequest.getBirthDate()))
                    .cpf(technicianRequest.getCpf())
                    .regDate(LocalDate.now())
                    .email(technicianRequest.getEmail())
                    .password(technicianRequest.getPassword())
                    .build();

            technicianRepository.save(technician);
            log.info("Technician {} saved successfully", technician.getId());
        }catch (NullPointerException e){
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    @Override
    @Transactional
    public void updateTechnician(Long id, CreateTechnicianRequest technicianRequest) {

        try {
            Technician technician = getTechnicianById(id);
            technician.setFirstName(technicianRequest.getFirstName());
            technician.setLastName(technicianRequest.getLastName());
            technician.setBirthDate(LocalDate.parse(technicianRequest.getBirthDate()));
            technician.setCpf(technicianRequest.getCpf());
            technician.setEmail(technicianRequest.getEmail());
            technician.setPassword(technicianRequest.getPassword());

            technicianRepository.save(technician);
            log.info("Technician {} updated successfully", technician.getId());
        }catch (NullPointerException e){
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    @Override
    @Transactional
    public void deleteTechnicianById(Long id) {

        try {
            technicianRepository.deleteById(id);
            log.info("Technician {} deleted successfully", id);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    @Override
    @Transactional
    public void deleteTechnician(TechnicianRequest technicianRequest) {

        try {
            technicianRepository.delete(technicianRepository.getTechnicianByEmail(technicianRequest.getEmail()));
            log.info("Technician {} deleted successfully", technicianRequest.getFirstName() + technicianRequest.getLastName());
        }catch (Exception e){
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }
}
