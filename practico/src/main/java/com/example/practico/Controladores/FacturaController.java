package com.example.practico.Controladores;

import com.example.practico.Entidades.Cliente;
import com.example.practico.Entidades.Factura;
import com.example.practico.Repository.FacturaRepository;
import com.example.practico.Request.FacturaRequest;
import com.example.practico.Services.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ldap.embedded.EmbeddedLdapProperties;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/factura")
public class FacturaController {
    @Autowired
    private FacturaService facturaService;
    @Autowired
    private FacturaRepository facturaRepository;

    //Create Post
    // http://locarhost:8080/factura
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> crearFactura(@RequestBody FacturaRequest facturaRequest) {
        try {
            Factura facturaAGuardar = new Factura(
                    facturaRequest.getFecha(),
                    facturaRequest.getCantidad(),
                    facturaRequest.getTotal()
            );
            return ResponseEntity.ok(facturaService.crearFactura(facturaAGuardar));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getStackTrace());
        }
    }
//Devuelve la factura por el numero de id

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> buscarFacturaPorId(@PathVariable(name = "id") Long id) {
        Optional<Factura> posibleFactura = facturaService.buscarFacturaPorId(id);
        if (posibleFactura.isPresent()) {
            return ResponseEntity.of(posibleFactura);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Borra una factura por numero de ID
    //http://localhost:8080/cliente/id
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> borrarFacturaPorID(@PathVariable(name = "id") Long id) {
        facturaRepository.deleteById(id);
        return ResponseEntity.ok("Factura con ID= " + id + " ha sido borrada");
    }

    //Actuilizar una factura por numero de id
    //http://localhost:8080/factura/id
    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> modificarUnaFactura(@RequestBody Factura factura) {
        try {
            Optional<Factura> posibleFactura = facturaService.buscarFacturaPorId(factura.getId());
            if (posibleFactura.isPresent()) {
                Factura facturaGuardada = posibleFactura.get();
                facturaGuardada.setFecha(factura.getFecha());
                facturaGuardada.setCantidad(factura.getCantidad());
                facturaGuardada.setTotal(factura.getTotal());

                facturaService.crearFactura(facturaGuardada);

                return ResponseEntity.ok().body(facturaGuardada);
            }else{
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getStackTrace());
        }
    }

        // devuelve todas las facturas de la lista
        //http://localhost:8080/cliente/todasLasFacturas
    @GetMapping(value = "/todasLasFacturas",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> todasLasFacturas(){
        return ResponseEntity.ok().body(facturaService.todasLasFacturas());
    }
}
