package com.emprestimo.empresax.model.common.enumeration;

public enum StatusPayment {

	PAGO("Pago"),
	NAO_PAGO("Não Pago");
	
	private final String status;
	
	StatusPayment(String status){
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}
}
