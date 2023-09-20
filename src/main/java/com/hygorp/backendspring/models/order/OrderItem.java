package com.hygorp.backendspring.models.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hygorp.backendspring.models.pk.OrderItemPK;
import com.hygorp.backendspring.models.product.Product;
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
public class OrderItem implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    @EqualsAndHashCode.Include
    private OrderItemPK id = new OrderItemPK();

    @Getter
    @Setter
    private Integer quantity;

    @Getter
    @Setter
    private Double price;

    public OrderItem(Order order, Product product, Integer quantity, Double price) {
        id.setOrder(order);
        id.setProduct(product);
        this.quantity = quantity;
        this.price = price;
    }

    @JsonIgnoreProperties(value = {"items", "product", "categories", "client"})
    public Order getOrder() {
        return id.getOrder();
    }

    public void setOrder(Order order) {
        id.setOrder(order);
    }

    public Product getProduct() {
        return id.getProduct();
    }

    public void getProduct(Product product) {
        id.setProduct(product);
    }

    public Double getSubtotal() {
        return getQuantity() * getPrice();
    }
}
