package com.aspire.controller;


import com.aspire.exceptions.NotFoundException;
import com.aspire.model.Loan;
import com.aspire.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/loan")
public class AdminController {

    @Autowired
    LoanService loanService;

    @PutMapping("/{loanId}/approve")
    public Loan approveLoan(@PathVariable Long loanId) throws NotFoundException {
        return loanService.approveLoan(loanId);
    }
}
