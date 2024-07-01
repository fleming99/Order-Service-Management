package com.fleming99.technician_microservice.controllers;

import com.fleming99.technician_microservice.application.TechnicianServiceImpl;
import com.fleming99.technician_microservice.core.dto.CreateTechnicianRequest;
import com.fleming99.technician_microservice.core.dto.TechnicianRequest;
import com.fleming99.technician_microservice.core.dto.TechnicianResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/technicians")
public class TechnicianController {

    private final TechnicianServiceImpl technicianService;

    @Autowired
    public TechnicianController(TechnicianServiceImpl technicianService) {
        this.technicianService = technicianService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TechnicianResponse> getTechniciansList(){
        return technicianService.getTechniciansResponseList();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TechnicianResponse getTechnicianResponse(@PathVariable Long id){
        return technicianService.getTechnicianResponseById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTechnician(@RequestBody CreateTechnicianRequest createTechnicianRequest){
        technicianService.createTechnician(createTechnicianRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateTechnician(@PathVariable Long id, @RequestBody CreateTechnicianRequest createTechnicianRequest){
        technicianService.updateTechnician(id, createTechnicianRequest);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteTechnician(@RequestBody TechnicianRequest technicianRequest){
        technicianService.deleteTechnician(technicianRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTechnicianById(@PathVariable Long id){
        technicianService.deleteTechnicianById(id);
    }
}
