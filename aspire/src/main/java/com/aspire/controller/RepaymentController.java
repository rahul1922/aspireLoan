package com.aspire.controller;

import com.aspire.dtos.RepaymentRequest;
import com.aspire.exceptions.NotFoundException;
import com.aspire.model.Repayment;
import com.aspire.services.RepaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/loan")
public class RepaymentController {

    @Autowired
    RepaymentService repaymentService;

    @PostMapping("/{loanId}/repayments")
    public List<Repayment> addRepayment(@PathVariable Long loanId, @RequestBody RepaymentRequest repaymentRequest) throws NotFoundException {
        return repaymentService.addRepayment(loanId, repaymentRequest);
    }
}
