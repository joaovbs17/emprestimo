package com.emprestimo.empresax.controller.loan;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emprestimo.empresax.request.loan.LoanRequest;
import com.emprestimo.empresax.response.loan.LoanResponse;
import com.emprestimo.empresax.service.loan.LoanService;

@RestController
@RequestMapping("/emprestimo")
public class LoanController {

	@Autowired
	private LoanService service;
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public LoanResponse create(@RequestBody @Valid LoanRequest request) {
		
		return service.create(request);
	}
}
