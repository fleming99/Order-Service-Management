package com.fleming99.technician_microservice.core.usecases;

import com.fleming99.technician_microservice.core.dto.CreateTechnicianRequest;
import com.fleming99.technician_microservice.core.dto.TechnicianRequest;
import com.fleming99.technician_microservice.core.dto.TechnicianResponse;
import com.fleming99.technician_microservice.core.entities.Technician;

import java.util.List;

public interface TechnicianUseCase {

    List<TechnicianResponse> getTechniciansResponseList();

    TechnicianResponse getTechnicianResponseById(Long id);

    Technician getTechnicianById(Long id);

    void createTechnician(CreateTechnicianRequest technicianRequest);

    void updateTechnician(Long id, CreateTechnicianRequest technicianRequest);

    void deleteTechnicianById(Long id);

    void deleteTechnician(TechnicianRequest technicianRequest);
}
