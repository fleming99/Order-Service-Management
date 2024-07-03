package com.fleming99.os_management_microservice.core.usecases;

import com.fleming99.os_management_microservice.core.dto.OrderRequest;
import com.fleming99.os_management_microservice.core.dto.OrderResponse;
import com.fleming99.os_management_microservice.core.entities.OrderService;

import java.util.List;

public interface OrderServiceUseCase {

    List<OrderResponse> getOrderServicesList();

    OrderResponse getOrderServiceResponseById(Long id);

    OrderService getOrderServiceById(Long id);

    void createOrderService(OrderRequest orderRequest);

    void updateOrderService(Long id, OrderRequest orderRequest);

    void closeOrderService(Long id, OrderRequest orderRequest);

    void deleteOrderServiceById(Long id);
}
