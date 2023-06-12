package com.aspire;

import com.aspire.dtos.LoanRequest;
import com.aspire.enums.LoanState;
import com.aspire.enums.RepaymentState;
import com.aspire.model.Loan;
import com.aspire.model.Repayment;
import com.aspire.services.LoanService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class AspireLoanProjectTests {

	LoanService loanService;

	@Test
	void contextLoads() {
	}

	@BeforeEach
	public void setUp() {
		loanService = new LoanService();
	}

	@Test
	public void testGenerateNewLoan(){
		LoanRequest loanRequest = new LoanRequest();
		loanRequest.setTerm(3);
		loanRequest.setAmount(BigDecimal.valueOf(1000));
		loanRequest.setDate(LocalDate.of(2022, 2, 7));
		Integer customerId = 1;
		Loan createdLoan = loanService.generateNewLoan(loanRequest, customerId);
		Assertions.assertNotNull(createdLoan);
		Assertions.assertEquals(BigDecimal.valueOf(1000), createdLoan.getAmount());
		Assertions.assertEquals(3, createdLoan.getTerm());
		Assertions.assertEquals(LoanState.PENDING, createdLoan.getState());
		Assertions.assertEquals(1, createdLoan.getCustomer());

	}

}
