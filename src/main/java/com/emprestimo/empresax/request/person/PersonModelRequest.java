package com.emprestimo.empresax.request.person;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.emprestimo.empresax.model.common.enumeration.IdentifierType;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonModelRequest {

	@NotBlank(message = "O campo identificador não pode estar em branco")
	@JsonProperty("identificador")
	public String identifier;
	
	@NotBlank(message = "O campo nome não pode estar em branco")
	@JsonProperty("nome")
	public String name;
	
	@NotNull(message = "Informe a data de nascimento")
	@JsonProperty("dataNascimento")
	@DateTimeFormat(iso = ISO.DATE)
	public LocalDate dateBirth;
	
	@NotNull(message = "Informe tipo identificador")
    @Enumerated(EnumType.STRING)
	@JsonProperty("tipoIdentificador")
	public IdentifierType identifierType;
}
