package com.hygorp.backendspring.resources.order;

import com.hygorp.backendspring.models.order.Order;
import com.hygorp.backendspring.models.order.OrderDTO;
import com.hygorp.backendspring.repositories.order.OrderRepository;
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
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderResource {

    @Autowired
    OrderRepository orderRepository;

    @PostMapping
    @Operation(summary = "Save Order", description = "Must persist in database a new Order",
            tags = {"Orders"},
            responses = {
                    @ApiResponse(description = "Created", responseCode = "201",
                            content = @Content(schema = @Schema(implementation = OrderDTO.class))
                    ),

                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            },
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public ResponseEntity<Order> saveOrder(@RequestBody OrderDTO order) {
        Order newOrder = new Order(null, order.date(), order.status(), order.client(), null);
        orderRepository.save(newOrder);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newOrder.getId()).toUri();
        return ResponseEntity.created(uri).body(newOrder);
    }

    @GetMapping
    @Operation(summary = "Return All Orders", description = "Must return all Orders persisted in database",
            tags = {"Orders"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = OrderDTO.class))
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
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return ResponseEntity.ok().body(orders);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Return Order by ID", description = "Must return Order matched by passed ID",
            tags = {"Orders"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = OrderDTO.class))
                    ),

                    @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            },
            security = @SecurityRequirement(name = "bearerAuth")
    )
    public ResponseEntity<Optional<Order>> getOrderById(@PathVariable Long id) {
        Optional<Order> returnedOrder = orderRepository.findById(id);
        return ResponseEntity.ok().body(returnedOrder);
    }
}
