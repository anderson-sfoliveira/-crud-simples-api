package com.example.crud.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crud.model.Cliente;
import com.example.crud.model.Telefone;

public interface TelefoneRepository extends JpaRepository<Telefone, Long> {
    List<Telefone> findByCliente(Cliente cliente);
}
