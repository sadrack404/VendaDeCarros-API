package com.example.vendaCarros.controller;

import com.example.vendaCarros.model.Carro;
import com.example.vendaCarros.repository.CarroRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carros")
public class CarroController {
    @Autowired
    CarroRepository carroRepository;

    @GetMapping
    public List<Carro> listarTodos (){
        return carroRepository.findAll();
    }

    @GetMapping("/{codCar}")
    public ResponseEntity <Carro> listarUm (@PathVariable Long codCar) {
        Optional<Carro> carro = carroRepository.findById(codCar);
        return carro.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Carro> cadastrar (@RequestBody Carro carro){
        return ResponseEntity.ok(carroRepository.save(carro));
    }

    @PutMapping("/{id}")
    public ResponseEntity <Carro> atualizar (@PathVariable Long id, @RequestBody Carro carro){
        Optional <Carro> carroAtual = carroRepository.findById(id);
        if (carroAtual.isPresent()){
            BeanUtils.copyProperties(carro, carroAtual.get(), "id");
            Carro carroSalvo = carroRepository.save(carroAtual.get());
            return ResponseEntity.ok(carroSalvo);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{codCar}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir (@PathVariable Long codCar) {
        carroRepository.deleteById(codCar);
    }

}