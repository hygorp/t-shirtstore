package com.hygorp.backendspring.models.product.enums;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public enum ProductSizeEnum {

    SMALL("s"),
    MEDIUM("m"),
    LARGE("l"),
    X_LARGE("xl"),
    XX_LARGE("xxl"),
    XXX_LARGE("3xl");

    @Enumerated(EnumType.STRING)
    private final String size;

    ProductSizeEnum(String size) {
        this.size = size;
    }
}
