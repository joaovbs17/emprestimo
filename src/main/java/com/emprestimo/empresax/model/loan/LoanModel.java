package com.emprestimo.empresax.model.loan;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.emprestimo.empresax.model.common.BasicModel;
import com.emprestimo.empresax.model.person.PersonModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "emprestimo")
public class LoanModel extends BasicModel<LoanModel>{

	private static final long serialVersionUID = 4222562604577119644L;

	@ManyToOne
	@JoinColumn(nullable = false, name = "id_pessoa", referencedColumnName = "id")
	public PersonModel person;
	
	@Column(nullable = false, name = "valor_emprestimo")
	public BigDecimal loanValue;
	
	@Column(nullable = false, name = "numero_parcelas")
	public Integer numberInstallments;
	
	@Column(nullable = false, name = "status_pagamento")
	public String paymentStatus;
	
	@Column(nullable = false, name = "data_criacao")
	public LocalDate creationDate;
}
