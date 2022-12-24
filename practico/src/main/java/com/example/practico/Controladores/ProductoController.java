package com.example.practico.Controladores;

import com.example.practico.Entidades.Cliente;
import com.example.practico.Entidades.Factura;
import com.example.practico.Entidades.Producto;
import com.example.practico.Repository.ProductoRepository;
import com.example.practico.Request.ClienteRequest;
import com.example.practico.Request.ProductoRequest;
import com.example.practico.Services.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;
    @Autowired
    private ProductoRepository productoRepository;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> crearProducto(@RequestBody ProductoRequest productoRequest) {
        Producto productoAGuardar = new Producto(
              productoRequest.getDescripcion(),
                productoRequest.getCantidad(),
                productoRequest.getPrecio()
        );
        return ResponseEntity.ok(productoService.crearProducto(productoAGuardar));
    }
    @GetMapping(value = "leer/{id}")
    public ResponseEntity<?> obtenerProductoPorID(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(productoService.obtenerProductoPorID(id));
    }
    //Delete
    @DeleteMapping(value = "leer/{id}")
    public ResponseEntity<?> borrarProductoPorID(@PathVariable(name = "id") final Long id) {
        productoRepository.deleteById(id);
        return ResponseEntity.ok("Producto borrado");
    }
    //Put
    @PutMapping(value = "leer/{id}")
    public ResponseEntity<?> actualizarClientePorID(@PathVariable(name = "id") final Long id) {
        Producto productoBuscado = productoRepository.getReferenceById(id);
        return ResponseEntity.ok(productoBuscado);
    }
}

