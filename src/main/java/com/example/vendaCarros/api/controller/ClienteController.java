package com.example.vendaCarros.api.controller;

import com.example.vendaCarros.domain.exceptions.EntidadeException;
import com.example.vendaCarros.domain.exceptions.EntidadeNaoEncontradaException;
import com.example.vendaCarros.domain.model.Cliente;
import com.example.vendaCarros.domain.service.CadastroClienteService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    CadastroClienteService clienteService;

    @GetMapping
    public List<Cliente> listar() { return clienteService.lista(); }

    @GetMapping("/{id_cliente}")
    public Cliente listarId (@PathVariable Long id_cliente){
        return clienteService.validaUmCliente(id_cliente);
    }

    @PostMapping
    public Cliente adicionar (@RequestBody Cliente cliente){
        return clienteService.salvar(cliente);
    }

    @PutMapping("/{id_cliente}")
    public  Cliente alterar (@PathVariable Long id_cliente, @RequestBody Cliente cliente){

        Cliente clienteAtual = clienteService.validaUmCliente(id_cliente);
        BeanUtils.copyProperties(cliente, clienteAtual, "id_cliente");
        try {
            return clienteService.salvar(clienteAtual);
        } catch (EntidadeNaoEncontradaException e) {
            throw new EntidadeException(e.getMessage());
        }
    }

    @DeleteMapping("/{id_cliente}")
    public void excluir (@PathVariable Long id_cliente){
        clienteService.excluir(id_cliente);
    }
}