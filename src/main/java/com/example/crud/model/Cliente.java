package com.example.crud.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
    private Long id;

    @NotNull
    private String nome;

	@NotNull
    @Enumerated(EnumType.STRING)
    private TipoCliente tipo;

	@NotNull
	@Column(name = "cpf_cnpj", unique=true)
    private String cpfCnpj;

	@Column(name = "rg_ie")
    private String rgIe;

    private LocalDateTime dataCadastro;

    @NotNull
    private boolean ativo;

	@JsonIgnoreProperties("cliente") // ignora a propriedade "order" da classe OrderItem
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Telefone> telefones = new ArrayList<>();
}
