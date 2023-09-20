package com.hygorp.backendspring.models.order;

import com.hygorp.backendspring.models.client.Client;
import com.hygorp.backendspring.models.order.enums.OrderStatus;

import java.time.LocalDateTime;

public record OrderDTO(LocalDateTime date, OrderStatus status, Client client) {
}
