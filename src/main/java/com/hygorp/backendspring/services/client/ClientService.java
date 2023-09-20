package com.hygorp.backendspring.services.client;

import com.hygorp.backendspring.models.client.Client;
import com.hygorp.backendspring.models.client.ClientDTO;
import com.hygorp.backendspring.repositories.client.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public void saveClient(Client client) {
        clientRepository.save(client);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElseThrow();
    }

    public Client updateClient(Long id, ClientDTO client){
        Client returnedClient = clientRepository.findById(id).orElseThrow();
        if(client.name() != null) {
            returnedClient.setName(client.name());
        }
        if(client.lastName() != null) {
            returnedClient.setLastName(client.lastName());
        }
        if(client.cpf() != null) {
            returnedClient.setCpf(client.cpf());
        }
        if(client.phone() != null) {
            returnedClient.setPhone(client.phone());
        }
        if(client.email() != null) {
            returnedClient.setEmail(client.email());
        }
        if(client.birthdate() != null) {
            returnedClient.setBirthdate(client.birthdate());
        }
        if(client.addresses() != null) {
            returnedClient.setAddresses(client.addresses());
        }

        return clientRepository.save(returnedClient);
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
