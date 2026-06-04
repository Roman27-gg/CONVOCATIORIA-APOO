package com.ut.sucursales.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "sucursal")
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "direccion", length = 100, unique = true)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String address;

    @Column(name = "nombre", unique = true, length = 100)
    private String name;

    @ManyToOne
    @JoinColumn(name = "franchise_id")
    private Franchise franchise;

    @Column(name = "nombre_sucursal", length = 100)
    private String branchName;

    @OneToMany(mappedBy = "branch", orphanRemoval = true)
    private Set<Product> products;

}