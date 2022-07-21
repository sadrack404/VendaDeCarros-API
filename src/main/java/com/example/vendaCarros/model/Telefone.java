package com.example.vendaCarros.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
public class Telefone {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long telefone_pk;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private String codCli_fk;
}
