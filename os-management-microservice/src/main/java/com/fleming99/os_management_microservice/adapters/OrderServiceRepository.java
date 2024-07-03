package com.fleming99.os_management_microservice.adapters;

import com.fleming99.os_management_microservice.core.entities.OrderService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderServiceRepository extends JpaRepository<OrderService, Long> {
}
