package com.hygorp.backendspring.models.order.enums;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public enum OrderStatus {

    PROCESSING("processing"),
    CONFIRMED("confirmed"),
    SHIPPED("shipped"),
    DELIVERED("delivered"),
    CANCELED("canceld");

    @Enumerated(EnumType.STRING)
    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }

}
