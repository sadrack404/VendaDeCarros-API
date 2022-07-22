package com.example.vendaCarros.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
public class Cliente {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_cliente;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cnh;

    @Embedded
    @Column
    private Endereco endereco;

    @Column
    @Embedded
    private Telefone telefone;
}
