package com.emprestimo.empresax.model.common.enumeration;

import java.math.BigDecimal;

public enum IdentifierType {
	PF("Pessoa Física", 11, new BigDecimal("300.00"), new BigDecimal("10000.00")),
	PJ("Pessoa Jurídica", 14, new BigDecimal("1000.00"), new BigDecimal("100000.00")),
	EU("Estudante Universitário", 8, new BigDecimal("100.00"), new BigDecimal("10000.00")),
	AP("Aposentado", 10, new BigDecimal("400.00"), new BigDecimal("25000.00"));

	private final String desciption;
	private final Integer identifierSize;
	private final BigDecimal minimumValueInstallments;
	private final BigDecimal maximumLoanAmount;

	IdentifierType(String desciption, Integer identifierSize, BigDecimal minimumValueInstallments, BigDecimal maximumLoanAmount) {
		this.desciption = desciption;
		this.identifierSize = identifierSize;
		this.minimumValueInstallments = minimumValueInstallments;
		this.maximumLoanAmount = maximumLoanAmount;
	}

	public String getDesciption() {
		return desciption;
	}
	
	public Integer getIdentifierSize() {
		return identifierSize;
	} 

	public BigDecimal getMinimumValueInstallments() {
		return minimumValueInstallments;
	}

	public BigDecimal getMaximumLoanAmount() {
		return maximumLoanAmount;
	}
}
