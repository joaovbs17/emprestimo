package com.emprestimo.empresax.repository.loan;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.emprestimo.empresax.model.loan.LoanModel;

@Repository
public interface LoanReposiotry extends CrudRepository<LoanModel, Long>{

	@Query("SELECT COUNT(l.id) > 0 FROM LoanModel l WHERE l.person.identifier = :identifier ")
	Boolean findByPeronIdentifier(@Param("identifier") String identifier);
	
	@Query("SELECT COALESCE(SUM(l.loanValue),0) FROM LoanModel l WHERE l.person.identifier = :identifier ")
	BigDecimal sumAllLoansByIdentifier(@Param("identifier") String identifier);
}
