package com.example.practico.Services;

import com.example.practico.Entidades.Cliente;
import com.example.practico.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
    public Cliente obtenerClientePorID(final Long id) {
        return clienteRepository.save(clienteRepository.getReferenceById(id));
    }
}
