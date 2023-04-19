package com.example.crud.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.model.Telefone;
import com.example.crud.service.TelefoneService;

@RestController
@RequestMapping("/api/clientes/{idCliente}/telefones")
public class TelefoneResource {

    @Autowired
    private TelefoneService telefoneService;

    @GetMapping
    public ResponseEntity<List<Telefone>> listar(@PathVariable Long idCliente) {
        List<Telefone> telefones = telefoneService.listarTelefonesPorCliente(idCliente);
        return ResponseEntity.ok(telefones);
    }

    @PostMapping
    public ResponseEntity<Telefone> criar(@PathVariable Long idCliente, @RequestBody Telefone telefone) {
        Telefone telefoneSalvo = telefoneService.salvarTelefone(idCliente, telefone);
        return ResponseEntity.status(HttpStatus.CREATED).body(telefoneSalvo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        telefoneService.excluirTelefone(id);
        return ResponseEntity.noContent().build();
    }
}