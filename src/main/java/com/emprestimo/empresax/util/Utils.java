package com.emprestimo.empresax.util;

import org.springframework.http.HttpStatus;

import com.emprestimo.empresax.exception.BaseException;
import com.emprestimo.empresax.model.common.enumeration.IdentifierType;

public class Utils {

	public static Boolean validateIdentifierType(IdentifierType identifierType, String identifier) {

		switch (identifierType) {
		case PF:
			return validateCpf(identifier);
		case PJ:
			return validateCnpj(identifier);
		case EU:
			return validateIdentifierUniversityStudent(identifier);
		case AP:
			return validateIdentifierRetired(identifier);
		default:
			return false;
		}
	}
	
	public static Boolean validateIdentifierSize(IdentifierType identifierType, String identifier) {
		
		identifier = identifier.replaceAll("[^0-9]", "");
	
		Integer length = identifier.length();
		
		if(length.compareTo(identifierType.getIdentifierSize()) == 0) {
			return true;
		}
		
		return false;
	}

	public static Boolean validateCpf(String cpf) {

		cpf = cpf.replaceAll("[^0-9]", "");

		if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
			throw new BaseException(HttpStatus.BAD_REQUEST, "A quantidade de dígitos numéricos para o identificador do tipo ' Pessoa Física ', deve ser "
					+ "11 digitos");
		}

		return true;
	}

	public static Boolean validateCnpj(String cnpj) {

		cnpj = cnpj.replaceAll("[^0-9]", "");

		if (cnpj.length() != 14) {
			throw new BaseException(HttpStatus.BAD_REQUEST, "A quantidade de dígitos numéricos para o identificador do tipo ' Pessoa Jurídica ', deve ser "
					+ "14 digitos");
		}

		return true;
	}

	public static Boolean validateIdentifierUniversityStudent(String identificador) {

		if (identificador.length() != 8) {
			throw new BaseException(HttpStatus.BAD_REQUEST, "A quantidade de dígitos numéricos para o identificador do tipo ' Estudante Universitário ', deve ser "
					+ "8 digitos");
		}

		char primeiroDigito = identificador.charAt(0);
		char ultimoDigito = identificador.charAt(identificador.length() - 1);

		int primeiroNumero = Character.getNumericValue(primeiroDigito);
		int ultimoNumero = Character.getNumericValue(ultimoDigito);

		if (primeiroNumero + ultimoNumero != 9) {
			throw new BaseException(HttpStatus.BAD_REQUEST, "A soma do primeiro e último dígito deve ser igual a 9.");
		}

		return true;
	}

	public static Boolean validateIdentifierRetired(String identificador) {

		if (identificador.length() != 10) {
			throw new BaseException(HttpStatus.BAD_REQUEST, "A quantidade de dígitos numéricos para o identificador do tipo ' Aposentado ', deve ser "
					+ "10 digitos");
		}

		String noUltimoDigito = identificador.substring(0, 9);
		String ultimoDigito = identificador.substring(9);

		if (noUltimoDigito.contains(ultimoDigito)) {
			throw new BaseException(HttpStatus.BAD_REQUEST, "O último dígito não pode estar presente nos outros 9 dígitos.");
		}

		return true;
	}
}
