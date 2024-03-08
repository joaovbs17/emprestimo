package com.emprestimo.empresax.service.person.impl;

import static java.util.Objects.isNull;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.emprestimo.empresax.exception.BaseException;
import com.emprestimo.empresax.mapper.person.PersonModelMapper;
import com.emprestimo.empresax.model.common.enumeration.IdentifierType;
import com.emprestimo.empresax.model.person.PersonModel;
import com.emprestimo.empresax.repository.loan.LoanReposiotry;
import com.emprestimo.empresax.repository.person.PersonModelRepository;
import com.emprestimo.empresax.request.person.PersonModelRequest;
import com.emprestimo.empresax.response.person.PersonModelResponse;
import com.emprestimo.empresax.service.person.PersonModelService;
import com.emprestimo.empresax.util.Utils;

import jakarta.transaction.Transactional;

@Service
public class PersonModelServiceImpl implements PersonModelService {

	@Autowired
	private PersonModelRepository repository;

	@Autowired
	private PersonModelMapper mapper;
	
	@Autowired
	private LoanReposiotry loanReposiotry;

	@Override
	public List<PersonModelResponse> listPersons(int page, int size) {

		PageRequest pageable = PageRequest.of(page, size);

		List<PersonModel> models = repository.findAll(pageable).getContent();

		return mapper.response(models);
	}

	@Override
	public PersonModelResponse getPerson(String identifier) {

		PersonModel model = find(identifier);

		return mapper.response(model);
	}

	@Override
	@Transactional
	public PersonModelResponse create(PersonModelRequest request) {

		duplicateIdentifier(request.identifier);
		
		validSize(request.identifierType, request.identifier);

		PersonModel model = mapper.create(request);
		
		model = repository.save(model);

		return mapper.response(model);
	}
	
	@Override
	@Transactional
	public PersonModelResponse update(String identifier, PersonModelRequest request) {

		validSize(request.identifierType, request.identifier);
		
		PersonModel model = find(identifier);
		
		duplicateIdentifier(request.identifier, model); 
	
		model = mapper.update(model, request);
		
		model = repository.save(model);
		
		return mapper.response(model);
	}
	
	private void validSize(IdentifierType identifierType, String identifier) {
		
		Boolean validSize = Utils.validateIdentifierSize(identifierType, identifier);

		if (!validSize) {
			throw new BaseException(HttpStatus.BAD_REQUEST, String.format("A quantidade de dígitos numéricos para o identificador do tipo ' %s ', deve ser "
					+ "%d digitos", identifierType.getDesciption(), identifierType.getIdentifierSize()));
		}
	}
	
	@Override
	public PersonModel find(String identifier) {
		
		PersonModel model = repository.findByIdentifier(identifier);

		if(isNull(model)) {
			throw new BaseException(HttpStatus.NOT_FOUND, "Pessoa não encontrada.");
		}
		
		return model;
	}

	@Override
	@Transactional
	public void delete(String identifier) {
		
		PersonModel model = find(identifier);
		checkAssociations(identifier);
		repository.deleteById(model.id);
	}
	
	private void checkAssociations(String identifier) {
		
		if(loanReposiotry.findByPeronIdentifier(identifier)) {
			throw new BaseException(HttpStatus.BAD_REQUEST, "Não será possível excluir essa pessoa pois, existe "
					+ "associação com emprestimo.");
		}
	}
	
	private void duplicateIdentifier(String identifier) {
		if(repository.existsByIdentifier(identifier)) {
			throw new BaseException(HttpStatus.CONFLICT, String.format("Já existe uma Pessoa cadastrada com esse identificador "
					+ "' %s '.", identifier));
		}
	}
	
	private void duplicateIdentifier(String identifier, PersonModel model) {
		
		if(!identifier.equals(model.identifier)) {
			if(repository.existsByIdentifier(identifier)) {
				throw new BaseException(HttpStatus.CONFLICT, String.format("Já existe uma Pessoa cadastrada com esse identificador "
						+ "' %s '.", identifier));
			}
		}
	}

}
