package com.example.practico.Services;

import com.example.practico.Entidades.Factura;
import com.example.practico.Entidades.Producto;
import com.example.practico.Repository.FacturaRepository;
import com.example.practico.Repository.ProductoRepository;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FacturaService {
    @Autowired
    private FacturaRepository facturaRepository;
    @Autowired
    private ProductoRepository productoRepository;

    public Factura crearFactura(Factura factura) {
        return facturaRepository.save(factura);
    }

    public Optional<Factura> buscarFacturaPorId(Long id) {
        return facturaRepository.findById(id);
    }

    // Lista de todas las facturas existente
    public List<Factura> todasLasFacturas() {
        return facturaRepository.findAll();
    }

    public void borrarFacturaPorId(Long id) {
        facturaRepository.deleteById(id);
    }
    //Metodo que recorre la lista productos y devuelve el precio total.


}
