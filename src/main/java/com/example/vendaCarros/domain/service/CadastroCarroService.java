package com.example.vendaCarros.domain.service;

import com.example.vendaCarros.domain.exceptions.EntidadeException;
import com.example.vendaCarros.domain.exceptions.EntidadeNaoEncontradaException;
import com.example.vendaCarros.domain.model.Carro;
import com.example.vendaCarros.domain.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroCarroService {

    public static final String MSG_CARRO_NAO_ENCONTRADO =
            "Não existe um cadastro de cidade com código %d";
    @Autowired
    CarroRepository carroRepository;

    public Carro salvar (Carro carro){
        return  carroRepository.save(carro);
    }

    public List<Carro> encontraTodos (){
        return carroRepository.findAll();
    }

    public void excluir (Long id_carro){
        try {
            carroRepository.deleteById(id_carro);
        }
        catch (EmptyResultDataAccessException e) {
            throw new EntidadeException(
                    String.format(MSG_CARRO_NAO_ENCONTRADO, id_carro)
            );
        }
    }

    public Carro validaCarro(Long id_carro) {
        return carroRepository.findById(id_carro)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(MSG_CARRO_NAO_ENCONTRADO, id_carro)));
    }

}