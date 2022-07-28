package com.example.vendaCarros.api.controller;

import com.example.vendaCarros.domain.exceptions.EntidadeException;
import com.example.vendaCarros.domain.exceptions.EntidadeNaoEncontradaException;
import com.example.vendaCarros.domain.model.Carro;
import com.example.vendaCarros.domain.service.CadastroCarroService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carros")
public class CarroController {
    @Autowired
    CadastroCarroService carroService;

    @GetMapping
    public List<Carro> listarTodos (){
        return carroService.encontraTodos();
    }

    @GetMapping("/{id_carro}")
    public Carro listarUm (@PathVariable Long id_carro) {
        return carroService.validaCarro(id_carro);
    }

    @PostMapping
    public Carro cadastrar (@RequestBody Carro carro){
        return carroService.salvar(carro);
    }

    @PutMapping("/{id_carro}")
    public Carro atualizar (@PathVariable Long id_carro, @RequestBody Carro carro){
        Carro carroAtual = carroService.validaCarro(id_carro);
        BeanUtils.copyProperties(carro, carroAtual, "id_carro");
        try {
            return carroService.salvar(carroAtual);
        } catch (EntidadeNaoEncontradaException e) {
            throw new EntidadeException(e.getMessage());
        }

    }

    @DeleteMapping("/{id_carro}")
    public void excluir (@PathVariable Long id_carro) {
        carroService.excluir(id_carro);
    }
}