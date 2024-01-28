package com.mitocode.controller;

import com.mitocode.model.Paciente;
import com.mitocode.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private IPacienteService service;

    @GetMapping
    public List<Paciente> listar() throws Exception {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Paciente listarPorid(@PathVariable("id") Integer id) throws Exception {
        return service.listarPorId(id);
    }

    @PostMapping
    public Paciente registrar(@RequestBody Paciente p) throws Exception {
        return service.registrar(p);
    }

    @PutMapping
    public Paciente modificar(@RequestBody Paciente p) throws Exception {
        return service.modificar(p);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable("id") Integer id) throws Exception {
        service.eliminar(id);
    }
}
