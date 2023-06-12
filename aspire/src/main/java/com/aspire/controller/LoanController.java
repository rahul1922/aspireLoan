package com.aspire.controller;

import com.aspire.dtos.LoanRequest;
import com.aspire.model.Loan;
import com.aspire.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/loan")
public class LoanController {

    @Autowired
    LoanService loanService;

    @PostMapping("/customer/{customerId}/create")
    public Loan createLoan(@RequestBody LoanRequest loanRequest, @PathVariable("customerId") Integer customerId) {
        return loanService.createLoan(loanRequest, customerId);
    }


    @GetMapping("/customer/{customerId}")
    public List<Loan> getCustomerLoans(@PathVariable("customerId") Integer customerId) {
        return loanService.getCustomerLoans(customerId);
    }
}

