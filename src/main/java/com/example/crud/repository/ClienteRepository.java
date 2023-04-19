package com.example.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crud.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Cliente findByCpfCnpj(String cpfCnpj);
    List<Cliente> findByNomeContaining(String nome);
    List<Cliente> findByNomeContainingAndAtivo(String nome, boolean ativo);
}
