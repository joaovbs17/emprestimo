package com.emprestimo.empresax.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class BaseException extends RuntimeException{
	private static final long serialVersionUID = 2174010346600961884L;
	private HttpStatus httpStatus;
    private String message;

}