package com.example.practico.Controladores;

import com.example.practico.Entidades.Cliente;
import com.example.practico.Entidades.Factura;
import com.example.practico.Repository.FacturaRepository;
import com.example.practico.Request.FacturaRequest;
import com.example.practico.Services.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(name = "/factura")
public class FacturaController {
    @Autowired
    private FacturaService facturaService;
    @Autowired
    private FacturaRepository facturaRepository;

    //Create Post
    // http://locarhost:8080/factura
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> crearFactura(@RequestBody FacturaRequest facturaRequest) {
        Factura facturaAGuardar = new Factura(
                facturaRequest.getFecha(),
                facturaRequest.getCantidad(),
                facturaRequest.getTotal()
        );
        return ResponseEntity.ok(facturaService.crearFactura(facturaAGuardar));
    }

    @GetMapping(value = "leer/{id}")
    public ResponseEntity<?> obtenerFacturaPorID(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(facturaService.obtenerFacturaPorID(id));
    }

    //Delete
    @DeleteMapping(value = "leer/{id}")
    public ResponseEntity<?> borrarFacturaPorID(@PathVariable(name = "id") final Long id) {
        facturaRepository.deleteById(id);
        return ResponseEntity.ok("Factura borrada");
    }
    //Put
    @PutMapping(value = "leer/{id}")
    public ResponseEntity<?> actualizarClientePorID(@PathVariable(name = "id") final Long id) {
        Factura facutaBuscada = facturaRepository.getReferenceById(id);
        return ResponseEntity.ok(facutaBuscada);
    }

}
