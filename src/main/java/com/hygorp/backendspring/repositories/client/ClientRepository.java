package com.hygorp.backendspring.repositories.client;

import com.hygorp.backendspring.models.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
