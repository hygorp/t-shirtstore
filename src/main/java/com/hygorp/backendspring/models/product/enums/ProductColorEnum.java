package com.hygorp.backendspring.models.product.enums;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public enum ProductColorEnum {

    BLACK("black"),
    BLUE("blue"),
    GRAY("gray"),
    GREEN("green"),
    PINK("pink"),
    YELLOW("yellow"),
    WHITE("white");

    @Enumerated(EnumType.STRING)
    private final String color;

    ProductColorEnum(String color) {
        this.color = color;
    }
}
