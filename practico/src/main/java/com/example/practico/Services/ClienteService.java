package com.example.practico.Services;

import com.example.practico.Entidades.Cliente;
import com.example.practico.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
    public Optional<Cliente> buscarPorId(Long id){
        return clienteRepository.findById(id);
    }

    //Lista de todos los clientes que existen
    public List<Cliente> todos(){
        return clienteRepository.findAll();
    }

    public void borrarPorId(Long id){
        clienteRepository.deleteById(id);
    }
}
