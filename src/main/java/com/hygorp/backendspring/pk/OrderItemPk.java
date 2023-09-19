package com.hygorp.backendspring.pk;

import com.hygorp.backendspring.models.order.Order;
import com.hygorp.backendspring.models.product.Product;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class OrderItemPk implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @EqualsAndHashCode.Include
    private Order order;

    @ManyToOne
    @EqualsAndHashCode.Include
    @JoinColumn(name = "product_id")
    private Product product;

}
