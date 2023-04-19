package com.example.crud.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.crud.model.Cliente;
import com.example.crud.repository.ClienteRepository;
import com.example.crud.service.exception.ContaCpfCnpjDuplicadoException;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> buscarClientesPorNomeOuAtivos(String nome, boolean ativo) {
        return clienteRepository.findByNomeContainingAndAtivo(nome, ativo);
    }

    public List<Cliente> buscarClientesPorNome(String nome) {
        return clienteRepository.findByNomeContaining(nome);
    }

    
    public Cliente adicionarCliente(Cliente cliente) {
    	validarCpfCnpjUnico(cliente.getCpfCnpj());
        cliente.setId(null);
        return clienteRepository.save(cliente);
    }

    @Transactional
    public Cliente atualizarCliente(Long id, Cliente cliente) {
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new EmptyResultDataAccessException(1));
        if (clienteExistente != null) {
            if (!clienteExistente.getCpfCnpj().equals(cliente.getCpfCnpj())) {
                validarCpfCnpjUnico(cliente.getCpfCnpj());
            }
        }
        BeanUtils.copyProperties(cliente, clienteExistente, new String[] {"id", "telefones"});
        return clienteRepository.save(clienteExistente);
    }

    public void removerCliente(Long id) {
    	Optional<Cliente> clienteExistente = clienteRepository.findById(id);
    	
    	if (clienteExistente.isPresent()) {
    		clienteRepository.deleteById(id);
        }
    }

    private void validarCpfCnpjUnico(String cpfCnpj) {
        Cliente clienteExistente = clienteRepository.findByCpfCnpj(cpfCnpj);
        if (clienteExistente != null) {
            throw new ContaCpfCnpjDuplicadoException();
        }
    }

}
