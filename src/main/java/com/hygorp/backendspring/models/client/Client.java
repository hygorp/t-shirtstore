package com.hygorp.backendspring.models.client;

import com.hygorp.backendspring.models.address.Address;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity(name = "tb_client")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Client implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;
    private String name;
    private String lastName;
    private String cpf;
    private String phone;
    private String email;
    private LocalDate birthdate;
    @ManyToMany
    @JoinTable(name = "tb_client_addresses", joinColumns = @JoinColumn(name = "client_id"), inverseJoinColumns = @JoinColumn(name = "address_id"))
    private Set<Address> addresses;
}
