package com.hygorp.backendspring.repositories.address;

import com.hygorp.backendspring.models.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressCategory extends JpaRepository<Address, Long> {
}
