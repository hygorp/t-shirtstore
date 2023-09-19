package com.hygorp.backendspring.models.product.enums;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public enum ProductGenreEnum {

    FEMALE("female"),
    MALE("male");

    @Enumerated(EnumType.STRING)
    private final String genre;

    ProductGenreEnum(String genre) {
        this.genre = genre;
    }
}
