package com.example.crud.model;

import lombok.Getter;

@Getter
public enum TipoCliente {
    PF("Pessoa Física"),
    PJ("Pessoa Jurídica");

    private String descricao;

    TipoCliente(String descricao) {
        this.descricao = descricao;
    }
}
