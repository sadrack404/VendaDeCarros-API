package com.example.vendaCarros.model;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Telefone {

    private String ddd;
    private String telefone;
    private String celular;

}
