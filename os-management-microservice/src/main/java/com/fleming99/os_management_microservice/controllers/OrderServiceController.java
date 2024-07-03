package com.fleming99.os_management_microservice.controllers;

import com.fleming99.os_management_microservice.application.OrderServiceImpl;
import com.fleming99.os_management_microservice.core.dto.OrderRequest;
import com.fleming99.os_management_microservice.core.dto.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order-services")
public class OrderServiceController {

    @Autowired
    private OrderServiceImpl orderService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderResponse> getOrderServicesList(){
        return orderService.getOrderServicesList();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public OrderResponse getOrderResponseById(@PathVariable Long id){
        return orderService.getOrderServiceResponseById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrderService(@RequestBody OrderRequest orderRequest){
        orderService.createOrderService(orderRequest);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateOrderService(@PathVariable Long id, @RequestBody OrderRequest orderRequest){
        orderService.updateOrderService(id, orderRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOrderServiceById(@PathVariable Long id){
        orderService.deleteOrderServiceById(id);
    }

}
