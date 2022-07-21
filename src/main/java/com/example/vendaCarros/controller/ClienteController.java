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
    public List<Cliente> listar(){return clienteRepository.findAll();}

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> listarId(@PathVariable Long id){
       Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.isPresent() ? ResponseEntity.ok(cliente.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Cliente> adicionar(@RequestBody Cliente cliente){
        return ResponseEntity.ok(clienteRepository.save(cliente));
    }

    @PutMapping
    public  ResponseEntity<Cliente> alterar (@PathVariable Long id, @RequestBody Cliente cliente){
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isPresent()){
            BeanUtils.copyProperties(cliente, clienteOptional.get(), "id");
            clienteRepository.save(clienteOptional.get());
            return ResponseEntity.ok(clienteOptional.get());
        }
        return ResponseEntity.noContent().build();
    }

}