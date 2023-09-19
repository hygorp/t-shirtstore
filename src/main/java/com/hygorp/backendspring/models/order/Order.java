package com.hygorp.backendspring.models.order;

import com.hygorp.backendspring.models.order.enums.OrderStatus;
import com.hygorp.backendspring.models.client.Client;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "tb_order")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Order implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;

    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    private OrderStatus status;

    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> items = new HashSet<>();

    public Double getAmount() {
        double sum = 0;
        for(OrderItem x : items) {
            sum += x.subtotal();
        }

        return sum;
    }
}