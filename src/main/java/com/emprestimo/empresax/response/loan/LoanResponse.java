package com.emprestimo.empresax.response.loan;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.emprestimo.empresax.response.person.PersonModelResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoanResponse {

	@JsonProperty("pessoa")
	public PersonModelResponse person;
	
	@JsonProperty("valorEmprestimo")
	public BigDecimal loanValue;
	
	@JsonProperty("numeroParcelas")
	public Integer numberInstallments;
	
	@JsonProperty("statusPagamento")
	public String paymentStatus;
	
	@JsonProperty("dataCriacao")
	public LocalDate creationDate;
}
