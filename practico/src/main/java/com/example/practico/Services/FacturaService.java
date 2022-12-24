package com.example.practico.Services;

import com.example.practico.Entidades.Factura;
import com.example.practico.Repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacturaService {
    @Autowired
    private FacturaRepository facturaRepository;

    public Factura crearFactura(Factura factura){
        return facturaRepository.save(factura);
    }

    public Factura obtenerFacturaPorID(final long id){
        return facturaRepository.save(facturaRepository.getReferenceById(id));
    }
}
