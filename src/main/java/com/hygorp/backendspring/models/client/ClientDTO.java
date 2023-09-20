package com.hygorp.backendspring.models.client;

import com.hygorp.backendspring.models.address.Address;

import java.time.LocalDate;
import java.util.Set;

public record ClientDTO(
        String name,
        String lastName,
        String cpf,
        String phone,
        String email,
        LocalDate birthdate,
        Set<Address> addresses) {
}
