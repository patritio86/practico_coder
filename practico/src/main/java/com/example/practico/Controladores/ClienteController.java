package com.example.practico.Controladores;

import com.example.practico.Repository.ClienteRepository;
import com.example.practico.Request.ClienteRequest;
import com.example.practico.Entidades.Cliente;
import com.example.practico.Services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ClienteController {


    // @Autowired
    //   private ClienteRepository clienteRepository;


    //Inyectamos el clienteService
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ClienteRepository clienteRepository;

    //Create Post
    //http://localhost:8080/cliente

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> crearCliente(@RequestBody ClienteRequest clienteRequest) {
        Cliente clienteAGuardar = new Cliente(
                clienteRequest.getNombre(),
                clienteRequest.getApellido(),
                clienteRequest.getDni()
        );
        return ResponseEntity.ok(clienteService.crearCliente(clienteAGuardar));
    }

    //CREATE -------------------------
    //http://localhost:8080/cliente/crear1
/*    @GetMapping(value = "crear1")
    public ResponseEntity<?> crearCliente() {

        Cliente nuevoCliente = new Cliente(
                "Patricio",
                "Gonzalez",
                32574029
        );
        return ResponseEntity.ok(clienteRepository.save(nuevoCliente));
    }*/

    //http://localhost:8080/cliente/crear2
 /*   @GetMapping(value = "crear2")
    public ResponseEntity<?> crearCliente2() {

        Cliente nuevoCliente = new Cliente(
                "Mariana",
                "Velazquez",
                36535550
        );
        return ResponseEntity.ok(clienteRepository.save(nuevoCliente));
    }*/

    //READ -------------------------
    //http://localhost:8080/cliente/leer/1
    @GetMapping(value = "leer/{id}")
    public ResponseEntity<?> obtenerClientePorID(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(clienteService.obtenerClientePorID(id));
    }

    //Delete
    @DeleteMapping(value = "leer/{id}")
    public ResponseEntity<?> borrarClientePorID(@PathVariable(name = "id") final Long id) {
        clienteRepository.deleteById(id);
        return ResponseEntity.ok("cliente borrado");
    }

    //Put
    @PutMapping(value = "leer/{id}")
    public ResponseEntity<?> actualizarClientePorID(@PathVariable(name = "id") final Long id) {
        Cliente clienteBuscado = clienteRepository.getReferenceById(id);
        return ResponseEntity.ok(clienteBuscado);
    }

}
