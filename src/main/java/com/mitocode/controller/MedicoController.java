package com.mitocode.controller;

import com.mitocode.exception.ModeloNotFoundException;
import com.mitocode.model.Medico;
import com.mitocode.service.IMedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/medicos")
public class MedicoController {

	@Autowired
	private IMedicoService service;

	// TODO: 29/01/24 ResponseEntity -> ayuda para manejar los status q devuelva el servicio
	@GetMapping
	public ResponseEntity<List<Medico>> listar() throws Exception {
		List<Medico> lista = service.listar();
		return new ResponseEntity<List<Medico>>(lista, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Medico> listarPorId(@PathVariable("id") Integer id) throws Exception {
		Medico obj = service.listarPorId(id);
		if (obj.getIdMedico() == null){
			throw new ModeloNotFoundException("ID NO ENCONTRADO " +  id);
		}
		return new ResponseEntity<Medico>(obj, HttpStatus.OK);

	}

	// TODO: 30/01/24 EntityModel -. pertenece a hateoas 
	// TODO: 30/01/24 Este metodo está alineada con el nivel de madurez 3 de Richardson.
	@GetMapping("/hateoas/{id}")
	public EntityModel<Medico> listarPorIdHateoas(@PathVariable("id") Integer id) throws Exception {
		Medico obj = service.listarPorId(id);

		if (obj.getIdMedico() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}

		// localhost:8080/Medicos/{id}
		EntityModel<Medico> recurso = EntityModel.of(obj);

		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).listarPorId(id));

		recurso.add(link.withRel("Medico-recurso"));

		return recurso;

	}

	@PostMapping
	public ResponseEntity<Medico> registrar(@Valid @RequestBody Medico p) throws Exception {
		Medico obj = service.registrar(p);
		// TODO: 29/01/24 location ->  se arma: http://localhost:8080/Medicos/22
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdMedico()).toUri();
		return  ResponseEntity.created(location).build();
	}

	@PutMapping
	public ResponseEntity<Medico> modificar(@Valid @RequestBody Medico p) throws Exception {
		Medico obj = service.modificar(p);
		return new ResponseEntity<Medico>(obj, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
		Medico obj = service.listarPorId(id);
		if (obj == null){
			throw new ModeloNotFoundException("ID NO ENCONTRADO " +  id);
		}

		service.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);

	}
	
	
}
