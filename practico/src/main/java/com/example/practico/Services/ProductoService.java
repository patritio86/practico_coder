package com.example.practico.Services;

import com.example.practico.Entidades.Producto;
import com.example.practico.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Optional<Producto> buscarProductoPorId(Long id) {
        return productoRepository.findById(id);
    }

    public List<Producto> todosLosProductos() {
        return productoRepository.findAll();
    }

    public void borrarProductoPorId(Long id) {
        productoRepository.deleteById(id);
    }
}
