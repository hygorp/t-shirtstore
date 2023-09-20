package com.hygorp.backendspring.resources.order;

import com.hygorp.backendspring.models.order.Order;
import com.hygorp.backendspring.models.order.OrderDTO;
import com.hygorp.backendspring.repositories.order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderResource {

    @Autowired
    OrderRepository orderRepository;

    @PostMapping
    public ResponseEntity<Order> saveOrder(@RequestBody OrderDTO order) {
        Order newOrder = new Order(null, order.date(), order.status(), order.client(), null);
        orderRepository.save(newOrder);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newOrder.getId()).toUri();
        return ResponseEntity.created(uri).body(newOrder);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return ResponseEntity.ok().body(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Order>> getOrderById(@PathVariable Long id) {
        Optional<Order> returnedOrder = orderRepository.findById(id);
        return ResponseEntity.ok().body(returnedOrder);
    }
}
