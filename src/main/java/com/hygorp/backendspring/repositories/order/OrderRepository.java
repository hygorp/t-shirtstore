package com.hygorp.backendspring.repositories.order;

import com.hygorp.backendspring.models.order.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByClient_Id(Long id);
}
