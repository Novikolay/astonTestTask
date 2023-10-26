package com.aston.astonTestTask.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "customers")
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@Setter
public class Customer {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "customersIdSeq", sequenceName = "customers_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customersIdSeq")
    @NonNull
    private int id;

    @Column(name = "name")
    @NonNull
    private String name;

    @Column(name = "pin")
    @NonNull
    private int pin;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    private Set<Bill> bills;

}
