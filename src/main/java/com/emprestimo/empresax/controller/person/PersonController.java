package com.emprestimo.empresax.controller.person;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.emprestimo.empresax.request.person.PersonModelRequest;
import com.emprestimo.empresax.response.person.PersonModelResponse;
import com.emprestimo.empresax.service.person.PersonModelService;

@RestController
@RequestMapping("/pessoa")
public class PersonController {

	@Autowired
	private PersonModelService service;
	
	@GetMapping(path = "/paginado")
	public List<PersonModelResponse> listPersons(
			@RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int quantidadePorPagina) {
		
		return service.listPersons(pagina, quantidadePorPagina);
	}
	
	@GetMapping(path = "/identificador/{identificador}")
	public PersonModelResponse getPerson(@PathVariable(name = "identificador") String identificador) {
		
		return service.getPerson(identificador);
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PersonModelResponse create(@RequestBody @Valid PersonModelRequest request) {
		
		return service.create(request);
	}
	
	@PutMapping(path = "/identificador/{identificador}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public PersonModelResponse update(@PathVariable(name = "identificador") String identificador, @RequestBody @Valid PersonModelRequest request) {
		
		return service.update(identificador, request);
	}
	
	@DeleteMapping(path = "/identificador/{identificador}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("identificador") String identificador) {

		service.delete(identificador);
	}
}
