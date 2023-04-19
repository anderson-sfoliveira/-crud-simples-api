package com.example.crud.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "telefone")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
    private Long id;

    @NotNull
    private String numero;

	@JsonIgnoreProperties("telefones")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}