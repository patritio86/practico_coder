package com.example.practico.Controladores;

import com.example.practico.Repository.ClienteRepository;
import com.example.practico.Request.ClienteRequest;
import com.example.practico.Entidades.Cliente;
import com.example.practico.Services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

        try {
            Cliente clienteAGuardar = new Cliente(
                    clienteRequest.getNombre(),
                    clienteRequest.getApellido(),
                    clienteRequest.getDni()
            );
            return ResponseEntity.ok(clienteService.crearCliente(clienteAGuardar));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getStackTrace());
        }


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

    //devuelve el cliente por numero de id -------------------------
    //http://localhost:8080/cliente/id
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> buscarPorId(@PathVariable(name = "id") Long id) {
        Optional<Cliente> posibleCliente = clienteService.buscarPorId(id);
        if (posibleCliente.isPresent()) {
            return ResponseEntity.of(posibleCliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // borrar cliente por numero de id
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> borrarPorId(@PathVariable(name = "id") Long id){
        clienteService.borrarPorId(id);
        return ResponseEntity.ok().body("cliente con ID= " + id + " ha sido borrado");
    }

    //Put
    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
   public ResponseEntity<?> modificar(@RequestBody Cliente cliente){
        try{
            Optional<Cliente> posibleCliente= clienteService.buscarPorId(cliente.getId());

            if(posibleCliente.isPresent()){
                Cliente clienteGuardado = posibleCliente.get();
                clienteGuardado.setApellido(cliente.getApellido());
                clienteGuardado.setDni(cliente.getDni());
                clienteGuardado.setNombre(cliente.getNombre());

                clienteService.crearCliente(clienteGuardado);

                return ResponseEntity.ok().body(clienteGuardado);
            }else{
               return ResponseEntity.notFound().build();

            }
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getStackTrace());

        }
    }

    // devuelve todos los clientes de la lista
    //http://localhost:8080/cliente/todos
    @GetMapping(value = "/todos", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> todos() {
        return ResponseEntity.ok().body(clienteService.todos());
    }

}
