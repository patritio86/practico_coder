package com.example.practico.Services;

import com.example.practico.Entidades.Producto;
import com.example.practico.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    public Producto crearProducto(Producto producto){
        return productoRepository.save(producto);
    }
    public Producto obtenerProductoPorID(final Long id){
        return productoRepository.save(productoRepository.getReferenceById(id));
    }
}
