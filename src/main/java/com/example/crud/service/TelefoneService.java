package com.example.crud.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.crud.model.Cliente;
import com.example.crud.model.Telefone;
import com.example.crud.repository.ClienteRepository;
import com.example.crud.repository.TelefoneRepository;

@Service
public class TelefoneService {

    @Autowired
    private TelefoneRepository telefoneRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Telefone> listarTelefonesPorCliente(Long idCliente) {
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));

        return cliente.getTelefones();
    }

    @Transactional
    public Telefone salvarTelefone(Long idCliente, Telefone telefone) {
        Cliente cliente = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        
        telefone.setCliente(cliente);
        return telefoneRepository.save(telefone);
    }

    @Transactional
    public void excluirTelefone(Long idTelefone) {
    	Optional<Telefone> telefoneExistente = telefoneRepository.findById(idTelefone);
    	
    	if (telefoneExistente.isPresent()) {
    		telefoneRepository.deleteById(idTelefone);
        }
    }
}
