package com.fleming99.os_management_microservice.application;

import com.fleming99.os_management_microservice.adapters.OrderServiceRepository;
import com.fleming99.os_management_microservice.core.dto.OrderRequest;
import com.fleming99.os_management_microservice.core.dto.OrderResponse;
import com.fleming99.os_management_microservice.core.entities.OrderService;
import com.fleming99.os_management_microservice.core.usecases.OrderServiceUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderServiceUseCase {

    @Autowired
    private final OrderServiceRepository orderServiceRepository;

    @Override
    public List<OrderResponse> getOrderServicesList() {

        List<OrderService> orderServices = orderServiceRepository.findAll();

        return orderServices.stream().map(this::mapOrderServiceToOrderResponse).toList();
    }

    private OrderResponse mapOrderServiceToOrderResponse(OrderService orderService) {

        return OrderResponse.builder()
                .customerId(orderService.getCustomerId())
                .technicianId(orderService.getTechnicianId())
                .jobDescription(orderService.getJobDescription())
                .price(orderService.getPrice())
                .creationDate(orderService.getCreationDate())
                .closureDate(orderService.getClosureDate())
                .status(orderService.getStatus())
                .build();
    }

    @Override
    public OrderResponse getOrderServiceResponseById(Long id) {

        return mapOrderServiceToOrderResponse(getOrderServiceById(id));
    }

    @Override
    public OrderService getOrderServiceById(Long id) {

        Optional<OrderService> orderService = orderServiceRepository.findById(id);

        if (orderService.isEmpty()){
            throw new RuntimeException("Did not found the OS by id: " + id);
        }

        return orderService.get();
    }

    @Override
    public void createOrderService(OrderRequest orderRequest) {

        OrderService orderService = OrderService.builder()
                .creationDate(LocalDate.now())
                .closureDate(null)
                .customerId(orderRequest.getCustomerId())
                .technicianId(orderRequest.getTechnicianId())
                .jobDescription(orderRequest.getJobDescription())
                .price(orderRequest.getPrice())
                .status('O')
                .build();

        orderServiceRepository.save(orderService);
    }

    @Override
    public void updateOrderService(Long id, OrderRequest orderRequest) {

        OrderService orderService = getOrderServiceById(id);
        orderService.setCustomerId(orderRequest.getCustomerId());
        orderService.setTechnicianId(orderRequest.getTechnicianId());
        orderService.setJobDescription(orderRequest.getJobDescription());
        orderService.setPrice(orderRequest.getPrice());
        if (orderRequest.getStatus().describeConstable().isPresent()) {
            orderService.setStatus(orderRequest.getStatus());
        }

        orderServiceRepository.save(orderService);
    }

    @Override
    public void closeOrderService(Long id, OrderRequest orderRequest) {

        OrderService orderService = getOrderServiceById(id);
        orderService.setStatus('C');

        orderServiceRepository.save(orderService);
    }

    @Override
    public void deleteOrderServiceById(Long id) {
        orderServiceRepository.deleteById(id);
    }
}
