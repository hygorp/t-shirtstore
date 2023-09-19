package com.hygorp.backendspring.services.order;

import com.hygorp.backendspring.models.order.Order;
import com.hygorp.backendspring.repositories.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow();
    }

    public List<Order> getAllOrdersByClientId(Long id) {
        return orderRepository.findByClient_Id(id);
    }

}
