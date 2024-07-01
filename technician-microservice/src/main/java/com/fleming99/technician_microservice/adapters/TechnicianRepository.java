package com.fleming99.technician_microservice.adapters;

import com.fleming99.technician_microservice.core.entities.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicianRepository extends JpaRepository<Technician, Long> {

    @Query(value = "SELECT * FROM technicians_list WHERE technician_email = ?1", nativeQuery = true)
    Technician getTechnicianByEmail(String email);
}
