package com.mitocode.controller;

import com.mitocode.exception.ModeloNotFoundException;
import com.mitocode.model.Paciente;
import com.mitocode.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private IPacienteService service;

    // TODO: 29/01/24 ResponseEntity -> ayuda para manejar los status q devuelva el servicio
    @GetMapping
    public ResponseEntity<List<Paciente>> listar() throws Exception {
        List<Paciente> lista = service.listar();
        return new ResponseEntity<List<Paciente>>(lista, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> listarPorid(@PathVariable("id") Integer id) throws Exception {
        Paciente obj = service.listarPorId(id);
        if (obj.getIdPaciente() == null){
            throw new ModeloNotFoundException("ID NO ENCONTRADO " +  id);
        }
        return new ResponseEntity<Paciente>(obj, HttpStatus.OK);

    }

    // TODO: 29/01/24 @Valid -> Si no lo pones de nada sirven los @Size en los beans ya que no lo reconocera ni lo validara y te devolvera un badRequest 400
    @PostMapping
    public ResponseEntity<Paciente> registrar(@Valid @RequestBody Paciente p) throws Exception {
        Paciente obj = service.registrar(p);
        return new ResponseEntity<Paciente>(obj, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Paciente> modificar(@Valid @RequestBody Paciente p) throws Exception {
        Paciente obj = service.modificar(p);
        return new ResponseEntity<Paciente>(obj, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
        Paciente obj = service.listarPorId(id);
        if (obj.getIdPaciente() == null){
            throw new ModeloNotFoundException("ID NO ENCONTRADO " +  id);
        }

        service.eliminar(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

    }
}
