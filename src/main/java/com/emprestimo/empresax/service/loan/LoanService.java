package com.emprestimo.empresax.service.loan;

import com.emprestimo.empresax.request.loan.LoanRequest;
import com.emprestimo.empresax.response.loan.LoanResponse;

public interface LoanService {

	LoanResponse create(LoanRequest request);
}
