package com.hygorp.backendspring.models.address;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity(name = "tb_address")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Address implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;
    private String address;
    private String number;
    private String postalCode;
    private String city;
    private String state;
    private String country;

    @Column(columnDefinition = "TEXT")
    private String info;
    private Boolean main;
}