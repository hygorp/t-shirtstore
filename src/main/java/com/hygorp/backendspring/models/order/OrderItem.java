package com.hygorp.backendspring.models.order;

import com.hygorp.backendspring.models.product.Product;
import com.hygorp.backendspring.pk.OrderItemPk;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity(name = "tb_order_item")
@NoArgsConstructor
@Getter
@Setter
public class OrderItem implements Serializable {

    @Serial
    @EqualsAndHashCode.Include
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private OrderItemPk id = new OrderItemPk();
    private Integer quantity;
    private Double price;

    public OrderItem(Order order, Product product, Integer quantity, Double price) {
        super();
        id.setOrder(order);
        id.setProduct(product);
        this.quantity = quantity;
        this.price = price;
    }

    public Double subtotal() {
        return price * quantity;
    }
}