package com.emprestimo.empresax.service.person;

import java.util.List;

import com.emprestimo.empresax.model.person.PersonModel;
import com.emprestimo.empresax.request.person.PersonModelRequest;
import com.emprestimo.empresax.response.person.PersonModelResponse;

public interface PersonModelService {

	List<PersonModelResponse> listPersons(int page, int size);
	
	PersonModelResponse getPerson(String identifier);
	
	PersonModelResponse create(PersonModelRequest request);
	
	PersonModelResponse update(String identifier, PersonModelRequest request);
	
	void delete(String identifier);
	
	PersonModel find(String identifier);
}
