package com.mitocode.controller;

import com.mitocode.deto.ConsultaListaExamenDTO;
import com.mitocode.exception.ModeloNotFoundException;
import com.mitocode.model.Consulta;
import com.mitocode.service.IConsultaService;
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
@RequestMapping("/consultas")
public class ConsultaController {

	@Autowired
	private IConsultaService service;
	
	
	@GetMapping
	public ResponseEntity<List<Consulta>> listar() throws Exception {
		List<Consulta> lista = service.listar();
		return new ResponseEntity<List<Consulta>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Consulta> listarPorId(@PathVariable("id") Integer id) throws Exception {
		Consulta obj = service.listarPorId(id);
		
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
		return new ResponseEntity<Consulta>(obj, HttpStatus.OK);
	}
	
	@GetMapping("/hateoas/{id}")
	public EntityModel<Consulta> listarPorIdHateoas(@PathVariable("id") Integer id) throws Exception {
		Consulta obj = service.listarPorId(id);

		if (obj.getIdConsulta() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
		
		// localhost:8080/Consultas/{id}
		EntityModel<Consulta> recurso = EntityModel.of(obj);
		
		WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).listarPorId(id));
		
		recurso.add(link.withRel("Consulta-recurso"));
		
		return recurso;

	}
	
	/*@PostMapping
	public ResponseEntity<Consulta> registrar(@Valid @RequestBody Consulta p) throws Exception{
		Consulta obj = service.registrar(p);
		
		return new ResponseEntity<Consulta>(obj, HttpStatus.CREATED);
	}*/
	
	@PostMapping
	public ResponseEntity<Consulta> registrar(@Valid @RequestBody ConsultaListaExamenDTO p) throws Exception{
		Consulta obj = service.registrarTransaccional(p);
		
		// localhost:8080/Consultas/{2}
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdConsulta()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Consulta> modificar(@Valid @RequestBody Consulta p) throws Exception{
		Consulta obj = service.modificar(p);
		
		return new ResponseEntity<Consulta>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
		Consulta obj = service.listarPorId(id);
		
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}
		service.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	
	
}
