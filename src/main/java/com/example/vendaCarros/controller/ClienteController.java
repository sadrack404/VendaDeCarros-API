package com.example.vendaCarros.controller;

import com.example.vendaCarros.model.Cliente;
import com.example.vendaCarros.repository.ClienteRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> listar() { return clienteRepository.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> listarId (@PathVariable Long id){
       Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cliente> adicionar (@RequestBody Cliente cliente){
        return ResponseEntity.ok(clienteRepository.save(cliente));
    }

    @PutMapping("/{id}")
    public  ResponseEntity<Cliente> alterar (@PathVariable Long id, @RequestBody Cliente cliente){
        Optional<Cliente> clienteAtual = clienteRepository.findById(id);
        if (clienteAtual.isPresent()){
            BeanUtils.copyProperties(cliente, clienteAtual.get(), "id");
            var clienteSalvo = clienteRepository.save(clienteAtual.get());
            return ResponseEntity.ok(clienteSalvo);
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public void excluir (@PathVariable Long id){
        clienteRepository.deleteById(id);
    }
}