package com.hygorp.backendspring.repositories.order;

import com.hygorp.backendspring.models.order.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
