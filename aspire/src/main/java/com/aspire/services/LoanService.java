package com.aspire.services;

import com.aspire.dtos.LoanRequest;
import com.aspire.enums.LoanState;
import com.aspire.enums.RepaymentState;
import com.aspire.exceptions.NotFoundException;
import com.aspire.model.Loan;
import com.aspire.model.Repayment;
import com.aspire.repository.LoanRepository;
import com.aspire.repository.RepaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoanService {

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    RepaymentRepository repaymentRepository;


    @Transactional
    public Loan createLoan(LoanRequest loanRequest, Integer customerId) {
        Loan loan = generateNewLoan(loanRequest, customerId);
        // Generate scheduled repayments
        generateScheduledRepayments(loanRequest, loan);
        return loanRepository.save(loan);
    }

    public Loan generateNewLoan(LoanRequest loanRequest, Integer customerId) {
        Long loanId = System.currentTimeMillis();
        Loan loan = new Loan();
        loan.setCustomer(customerId);
        loan.setAmount(loanRequest.getAmount());
        loan.setTerm(loanRequest.getTerm());
        loan.setDate(loanRequest.getDate());
        loan.setState(LoanState.PENDING);
        loan.setLoanId(loanId);
        return loan;
    }

    public Loan approveLoan(Long loanId) throws NotFoundException {
            Loan loan = loanRepository.findByLoanId(loanId);
        loan.setState(LoanState.APPROVED);
        return loanRepository.save(loan);
    }

    public List<Loan> getCustomerLoans(Integer customerId) {
        return loanRepository.findAllByCustomer(customerId);
    }


    public List<Repayment> generateScheduledRepayments(LoanRequest loanRequest, Loan loan) {
        BigDecimal loanAmount = loanRequest.getAmount();
        int loanTerm = loanRequest.getTerm();
        LocalDate loanDate = loanRequest.getDate();

        BigDecimal repaymentAmount = loanAmount.divide(BigDecimal.valueOf(loanTerm), 2, RoundingMode.HALF_UP);

        List<Repayment> scheduledRepayments = new ArrayList<>();
        LocalDate repaymentDate = loanDate.plusWeeks(1); // Start with the first repayment one week after the loan date

        for (int i = 0; i < loanTerm; i++) {
            Repayment repayment = new Repayment();
            repayment.setAmount(repaymentAmount);
            repayment.setDate(repaymentDate);
            repayment.setState(RepaymentState.PENDING);
            repayment.setLoanId(loan.getLoanId());
            scheduledRepayments.add(repayment);
            repaymentRepository.save(repayment);
            repaymentDate = repaymentDate.plusWeeks(1); // Increment the repayment date by one week
        }

        return scheduledRepayments;
    }
}

