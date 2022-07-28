package com.example.vendaCarros.domain.service;

import com.example.vendaCarros.domain.exceptions.EntidadeException;
import com.example.vendaCarros.domain.exceptions.EntidadeNaoEncontradaException;
import com.example.vendaCarros.domain.model.Cliente;
import com.example.vendaCarros.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastroClienteService {

    public static final String CLIENTE_NAO_ENCONTRADO = "Cliente não encontrado %d";
    @Autowired
    ClienteRepository clienteRepository;

    public List<Cliente> lista(){
        return clienteRepository.findAll();
    }

    public Cliente salvar (Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public void excluir (Long id_Cliente){
        try {
            clienteRepository.deleteById(id_Cliente);
        }
        catch (EmptyResultDataAccessException e) {
            throw new EntidadeException(
                    String.format(CLIENTE_NAO_ENCONTRADO, id_Cliente)
            );
        }
    }

    public Cliente validaUmCliente (Long id_cliente) {
        return clienteRepository.findById(id_cliente).
                orElseThrow(() -> new EntidadeNaoEncontradaException(
                        String.format(CLIENTE_NAO_ENCONTRADO, id_cliente)));
    }

}
