package com.example.vendaCarros.api.controller;

import com.example.vendaCarros.domain.model.Cliente;
import com.example.vendaCarros.domain.repository.ClienteRepository;
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

    @GetMapping("/{id_cliente}")
    public ResponseEntity<Cliente> listarId (@PathVariable Long id_cliente){
       Optional<Cliente> cliente = clienteRepository.findById(id_cliente);
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cliente> adicionar (@RequestBody Cliente cliente){
        return ResponseEntity.ok(clienteRepository.save(cliente));
    }

    @PutMapping("/{id_cliente}")
    public  ResponseEntity<Cliente> alterar (@PathVariable Long id_cliente, @RequestBody Cliente cliente){
        Optional<Cliente> clienteAtual = clienteRepository.findById(id_cliente);
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