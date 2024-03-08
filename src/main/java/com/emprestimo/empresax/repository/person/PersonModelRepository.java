package com.emprestimo.empresax.repository.person;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.emprestimo.empresax.model.person.PersonModel;

@Repository
public interface PersonModelRepository extends CrudRepository<PersonModel, Long>{

	Page<PersonModel> findAll(Pageable pageable);
	
	PersonModel findByIdentifier(String identifier);
	
	Boolean existsByIdentifier(String identifier);
}
