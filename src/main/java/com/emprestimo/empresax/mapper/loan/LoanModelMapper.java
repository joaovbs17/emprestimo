package com.emprestimo.empresax.mapper.loan;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.emprestimo.empresax.mapper.person.PersonModelMapper;
import com.emprestimo.empresax.model.common.enumeration.StatusPayment;
import com.emprestimo.empresax.model.loan.LoanModel;
import com.emprestimo.empresax.model.person.PersonModel;
import com.emprestimo.empresax.request.loan.LoanRequest;
import com.emprestimo.empresax.response.loan.LoanResponse;

@Component
public class LoanModelMapper {
	
	@Autowired
	private PersonModelMapper personModelMapper;
	
	public LoanResponse response(LoanModel model) {
		
		return LoanResponse.builder()
				.person(personModelMapper.response(model.person))
				.loanValue(model.loanValue)
				.numberInstallments(model.numberInstallments)
				.paymentStatus(model.paymentStatus)
				.creationDate(model.creationDate)
				.build();
	}

	public LoanModel create(PersonModel personModel, LoanRequest request) {
		
		LoanModel model = new LoanModel();
		
		model.person = personModel;
		model.loanValue = request.loanValue;
		model.numberInstallments = request.numberInstallments;
		model.paymentStatus = StatusPayment.NAO_PAGO.getStatus();
		model.creationDate = LocalDate.now(); 
		
		return model;
	}
}
