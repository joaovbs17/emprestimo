package com.emprestimo.empresax.response.person;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PersonModelResponse {

	@NotBlank
	@JsonProperty("identificador")
	public String identifier;
	
	@NotBlank
	@JsonProperty("nome")
	public String name;
	
	@JsonProperty("dataNascimento")
	public LocalDate dateBirth;
	
	@NotBlank
	@JsonProperty("tipoIdentificador")
	public String identifierType;
	
	@NotNull
	@JsonProperty("valorMinimoMensal")
	public BigDecimal monthlyMinimum;
	
	@NotNull
	@JsonProperty("valorMaximoEmprestimo")
	public BigDecimal maximumLoanAmount;
}
