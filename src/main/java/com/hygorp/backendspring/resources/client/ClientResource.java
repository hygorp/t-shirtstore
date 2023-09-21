package com.hygorp.backendspring.resources.client;

import com.hygorp.backendspring.models.client.Client;
import com.hygorp.backendspring.models.client.ClientDTO;
import com.hygorp.backendspring.services.client.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
    @Operation(summary = "Save Client", description = "Must persist in database a new Client",
            tags = {"Clients"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201",
                            content = @Content(schema = @Schema(implementation = ClientDTO.class))
                    ),

                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            },
            security = @SecurityRequirement(name = "bearerAuth")
    )
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
    @Operation(summary = "Return All Clients", description = "Must return all Clients persisted in database",
            tags = {"Clients"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = ClientDTO.class))
                                    )
                            }
                    ),

                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            },
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        return ResponseEntity.ok().body(clients);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Return Client by ID", description = "Must return Client matched by passed ID",
            tags = {"Clients"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ClientDTO.class))
                    ),

                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            },
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        Client client = clientService.getClientById(id);
        return ResponseEntity.ok().body(client);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Edit Client", description = "Must save changes sent to the Client selected by ID",
            tags = {"Clients"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ClientDTO.class))
                    ),

                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            },
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody ClientDTO client){
        Client returnedClient = clientService.updateClient(id, client);
        return ResponseEntity.ok().body(returnedClient);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Client", description = "Must delete the Client corresponding to the passed id",
            tags = {"Clients"},
            responses = {
                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            },
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
