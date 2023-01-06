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

import java.util.Optional;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;
    @Autowired
    private ProductoRepository productoRepository;

    //Crear un producto., Post
    //http://localhost:8080/producto
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> crearProducto(@RequestBody ProductoRequest productoRequest) {
    try {
        Producto productoAGuardar = new Producto(
                productoRequest.getDescripcion(),
                productoRequest.getCantidad(),
                productoRequest.getPrecio()
        );
        return ResponseEntity.ok(productoService.crearProducto(productoAGuardar));
    }catch (Exception e){
        return ResponseEntity.internalServerError().body(e.getStackTrace());
    }
    }

    //devuelve un producto por numero de id.,GET

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> buscarPorId(@PathVariable(name = "id") Long id) {
        Optional<Producto> posibleProducto = productoService.buscarProductoPorId(id);
        if (posibleProducto.isPresent()) {
            return ResponseEntity.of(posibleProducto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Borrar un produto por numero de id., DELETE
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> borrarPorId(@PathVariable(name = "id") Long id) {
        productoService.borrarProductoPorId(id);
        return ResponseEntity.ok().body("El producto con ID= " + id + " ha sido borrado");
    }
    //Actualizar un producto.,PUT
    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> modificar(@RequestBody Producto producto) {
        try {
            Optional<Producto> posibleProducto = productoService.buscarProductoPorId(producto.getId());

            if (posibleProducto.isPresent()) {
                Producto productoGuardado = posibleProducto.get();
                productoGuardado.setDescripcion(producto.getDescripcion());
                productoGuardado.setCantidad(producto.getCantidad());
                productoGuardado.setPrecio(producto.getPrecio());

                productoService.crearProducto(productoGuardado);

                return ResponseEntity.ok().body(productoGuardado);
            } else {
                return ResponseEntity.notFound().build();

            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getStackTrace());

        }
    }

    // Devuelve toda la lista de productos., GET

    @GetMapping(value = "/todosLosProductos", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> todos() {
        return ResponseEntity.ok().body(productoService.todosLosProductos());
    }
}

