package com.hygorp.backendspring.models.category;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity(name = "tb_category")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Category implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;
    private String name;
}
