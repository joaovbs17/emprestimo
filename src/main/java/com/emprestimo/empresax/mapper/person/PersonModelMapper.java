package com.emprestimo.empresax.mapper.person;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.emprestimo.empresax.model.person.PersonModel;
import com.emprestimo.empresax.request.person.PersonModelRequest;
import com.emprestimo.empresax.response.person.PersonModelResponse;

@Component
public class PersonModelMapper {

	public PersonModelResponse response(PersonModel model) {
		
		return PersonModelResponse.builder()
				.identifier(model.identifier)
				.name(model.name)
				.dateBirth(model.dateBirth)
				.identifierType(model.identifierType.getDesciption())
				.monthlyMinimum(model.monthlyMinimum)
				.maximumLoanAmount(model.maximumLoanAmount)
				.build();
	}
	
	public List<PersonModelResponse> response(List<PersonModel> models) {
		
		return models.stream().map(this::response).collect(Collectors.toList());
	}
	
	public PersonModel create(PersonModelRequest request) {
		
		PersonModel model = new PersonModel();
		
		model.identifier = request.identifier;
		model.name = request.name;
		model.dateBirth = request.dateBirth;
		model.identifierType = request.identifierType;
		model.monthlyMinimum = request.identifierType.getMinimumValueInstallments();
		model.maximumLoanAmount = request.identifierType.getMaximumLoanAmount();
		
		return model;
	}
	
	public PersonModel update(PersonModel model, PersonModelRequest request) {
		
		model.identifier = request.identifier;
		model.name = request.name;
		model.dateBirth = request.dateBirth;
		model.identifierType = request.identifierType;
		model.monthlyMinimum = request.identifierType.getMinimumValueInstallments();
		model.maximumLoanAmount = request.identifierType.getMaximumLoanAmount();
		
		return model;
	}
}
