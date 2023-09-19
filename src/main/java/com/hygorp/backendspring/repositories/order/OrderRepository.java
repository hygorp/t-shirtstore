package com.hygorp.backendspring.repositories.order;

import com.hygorp.backendspring.models.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
