package com.ut.sucursales.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "usuario")
public class User {
    @Column(name = "nombre_usuario", unique = true, length = 100)
    private String username;

    @Column(name = "contraseña", length = 100)
    private String password;

    @Column(name = "email", unique = true, length = 100)
    private String email;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;



}