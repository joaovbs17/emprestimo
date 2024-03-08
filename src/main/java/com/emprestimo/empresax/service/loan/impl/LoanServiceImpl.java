package com.emprestimo.empresax.service.loan.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.util.Objects.isNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.emprestimo.empresax.exception.BaseException;
import com.emprestimo.empresax.mapper.loan.LoanModelMapper;
import com.emprestimo.empresax.model.common.enumeration.IdentifierType;
import com.emprestimo.empresax.model.loan.LoanModel;
import com.emprestimo.empresax.model.person.PersonModel;
import com.emprestimo.empresax.repository.loan.LoanReposiotry;
import com.emprestimo.empresax.request.loan.LoanRequest;
import com.emprestimo.empresax.response.loan.LoanResponse;
import com.emprestimo.empresax.service.loan.LoanService;
import com.emprestimo.empresax.service.person.PersonModelService;
import com.emprestimo.empresax.util.Utils;

@Service
public class LoanServiceImpl implements LoanService{

	@Autowired
	private PersonModelService personModelService;
	
	@Autowired
	private LoanReposiotry repository;
	
	@Autowired
	private LoanModelMapper mapper;
	
	@Override
	public LoanResponse create(LoanRequest request) {
		
		PersonModel personModel = personModelService.find(request.identifier);
		
		validIdentifier(personModel.identifierType, personModel.identifier);
		validateMaximumLoanAmount(personModel.identifierType, request.loanValue, request.numberInstallments, personModel.identifier);
		
		LoanModel model = mapper.create(personModel, request);
		
		model = repository.save(model);
		
		return mapper.response(model);
	}
	
	private void validateMaximumLoanAmount(IdentifierType identifierType, BigDecimal value, Integer number, String identifier) {
		
		if(isNull(value)) {
			throw new BaseException(HttpStatus.BAD_REQUEST, "Informe o valor desejado para realizar o emprestimo.");
		}
	
		BigDecimal sumAllLoans = repository.sumAllLoansByIdentifier(identifier);
		
		if(sumAllLoans.compareTo(BigDecimal.ZERO) == 1) {
			BigDecimal aux1 = sumAllLoans.add(value);
			BigDecimal aux2 = identifierType.getMaximumLoanAmount();
			if(aux2.compareTo(aux1) == -1) {
				throw new BaseException(HttpStatus.BAD_REQUEST, String.format("Para o tipo identificador %s, o valor máximo é %.2f. "
						+ "Esta pessoa já possui um emprestimos no valor de %.2f, informe por favor um valor menor", 
						identifierType.getDesciption(), identifierType.getMaximumLoanAmount(), sumAllLoans));
			}
		}else {
			if(identifierType.getMaximumLoanAmount().compareTo(value) == -1) {
				throw new BaseException(HttpStatus.BAD_REQUEST, String.format("Para o tipo identificador %s, o valor máximo é %.2f", 
						identifierType.getDesciption(), identifierType.getMaximumLoanAmount()));
			}
		}
		
		if(isNull(number)) {
			throw new BaseException(HttpStatus.BAD_REQUEST, "Informe o número de parcelas.");
		}
		
		if(number.compareTo(24) == 1) {
			throw new BaseException(HttpStatus.BAD_REQUEST, "O número de parcelas não pode ser maior que 24.");
		}
		
		BigDecimal portion = value.divide(new BigDecimal(number), RoundingMode.HALF_EVEN);
		
		if(identifierType.getMinimumValueInstallments().compareTo(portion) == 1) {
			throw new BaseException(HttpStatus.BAD_REQUEST, String.format("Para o tipo identificador %s, o valor mínimo das parcelas "
					+ "é %.2f", identifierType.getDesciption(), identifierType.getMinimumValueInstallments()));
		}
	}
	
	private void validIdentifier(IdentifierType identifierType, String identifier) {
		
		Boolean result = Utils.validateIdentifierType(identifierType, identifier);

		if (!result) {
			throw new BaseException(HttpStatus.BAD_REQUEST, "Identificador invalido");
		}
	}

}
