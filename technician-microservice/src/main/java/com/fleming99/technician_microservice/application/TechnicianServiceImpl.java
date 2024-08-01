package com.fleming99.technician_microservice.application;

import com.fleming99.technician_microservice.adapters.TechnicianRepository;
import com.fleming99.technician_microservice.core.dto.CreateTechnicianRequest;
import com.fleming99.technician_microservice.core.dto.TechnicianResponse;
import com.fleming99.technician_microservice.core.dto.UpdateTechnicianRequest;
import com.fleming99.technician_microservice.core.entities.Technician;
import com.fleming99.technician_microservice.core.usecases.TechnicianUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
                .id(technician.getId())
                .firstName(technician.getFirstName())
                .lastName(technician.getLastName())
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
    public Technician createTechnician(CreateTechnicianRequest technicianRequest) {

        try {
            Technician technician = Technician.builder()
                    .firstName(technicianRequest.getFirstName())
                    .lastName(technicianRequest.getLastName())
                    .birthDate(technicianRequest.getBirthDate())
                    .cpf(technicianRequest.getCpf())
                    .rg(technicianRequest.getRg())
                    .regDate(LocalDate.now())
                    .admissionDate(technicianRequest.getAdmissionDate())
                    .status('A')
                    .email(technicianRequest.getEmail())
                    .build();


            log.info("Trying to create the Technician: {}", technician.getId());
            return technicianRepository.save(technician);
        }catch (NullPointerException e){
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Technician updateTechnician(Long id, UpdateTechnicianRequest technicianRequest) {

        try {
            Technician technician = getTechnicianById(id);
            technician.setFirstName(technicianRequest.getFirstName());
            technician.setLastName(technicianRequest.getLastName());
            technician.setBirthDate(technicianRequest.getBirthDate());
            technician.setCpf(technicianRequest.getCpf());
            technician.setRg(technicianRequest.getRg());
            technician.setAdmissionDate(technicianRequest.getAdmissionDate());
            technician.setResignationDate(technicianRequest.getResignationDate());
            technician.setEmail(technicianRequest.getEmail());

            if (technician.getResignationDate() != null){
                technician.setStatus('D');
            }else{
                technician.setStatus('A');
            }

            log.info("Trying to update the Technician: {}", technician.getId());
            return technicianRepository.save(technician);
        }catch (NullPointerException e){
            throw new RuntimeException(e.getMessage(), e.getCause());
        }
    }
}
