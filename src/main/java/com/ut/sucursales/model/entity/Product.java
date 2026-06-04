package com.ut.sucursales.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "producto")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "precio", precision = 19, scale = 2)
    private BigDecimal price;

    @Column(name = "nombre", length = 100)
    private String name;

    @Column(name = "stock")
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "branch_id")
    private Branch branch;

}