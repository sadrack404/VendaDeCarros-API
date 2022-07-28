package com.example.vendaCarros.domain.model;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Endereco {
    private String rua;
    private String quadra;
    private String cidade;
}