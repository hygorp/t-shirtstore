package com.hygorp.backendspring.resources.client;

import com.hygorp.backendspring.models.client.Client;
import com.hygorp.backendspring.models.client.ClientDTO;
import com.hygorp.backendspring.services.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientResource {

    @Autowired
    ClientService clientService;

    @PostMapping
    public ResponseEntity<Client> saveClient(@RequestBody ClientDTO client) {
        Client newClient = new Client(
                null,
                client.name(),
                client.lastName(),
                client.cpf(),
                client.phone(),
                client.email(),
                client.birthdate(),
                client.addresses()
        );
        clientService.saveClient(newClient);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newClient.getId()).toUri();
        return ResponseEntity.created(uri).body(newClient);
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        return ResponseEntity.ok().body(clients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        Client client = clientService.getClientById(id);
        return ResponseEntity.ok().body(client);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody ClientDTO client){
        Client returnedClient = clientService.updateClient(id, client);
        return ResponseEntity.ok().body(returnedClient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
