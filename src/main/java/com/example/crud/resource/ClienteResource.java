package com.example.crud.resource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.exceptionhandler.ApiEmpresaExceptionHandler.Erro;
import com.example.crud.model.Cliente;
import com.example.crud.repository.ClienteRepository;
import com.example.crud.service.ClienteService;
import com.example.crud.service.exception.ContaCpfCnpjDuplicadoException;

@RestController
@RequestMapping("/api/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public ResponseEntity<List<Cliente>> listar(@RequestParam String nome, @RequestParam Boolean ativo) {
    	List<Cliente> clientes;
    	
    	if (ativo) {
    		clientes = clienteService.buscarClientesPorNomeOuAtivos(nome, ativo);
    	} else {
    		clientes = clienteService.buscarClientesPorNome(nome);
    	}
        
        return ResponseEntity.ok(clientes);
    }

	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPeloCodigo(@PathVariable Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return !cliente.isEmpty() ? ResponseEntity.ok(cliente) : ResponseEntity.notFound().build();
	}

    @PostMapping
    public ResponseEntity<Cliente> criar(@Valid @RequestBody Cliente cliente) {
        Cliente clienteSalvo = clienteService.adicionarCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @Valid @RequestBody Cliente cliente) {
        Cliente clienteAtualizado = clienteService.atualizarCliente(id, cliente);
        return ResponseEntity.ok(clienteAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        clienteService.removerCliente(id);
        return ResponseEntity.noContent().build();
    }
    
	@ExceptionHandler({ ContaCpfCnpjDuplicadoException.class })
	public ResponseEntity<Object> handleContaCpfCnpjDuplicadoException(ContaCpfCnpjDuplicadoException ex) {
		String mensagemUsuario = "Já existe um cliente cadastrado com o CPF/CNPJ informado.";
		String mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
	}
}
