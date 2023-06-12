package com.aspire.services;


import com.aspire.dtos.RepaymentRequest;
import com.aspire.enums.LoanState;
import com.aspire.enums.RepaymentState;
import com.aspire.exceptions.NotFoundException;
import com.aspire.model.Loan;
import com.aspire.model.Repayment;
import com.aspire.repository.LoanRepository;
import com.aspire.repository.RepaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class RepaymentService {

    @Autowired
    LoanRepository loanRepository;

    @Autowired
    RepaymentRepository repaymentRepository;

    public RepaymentService(LoanRepository loanRepository, RepaymentRepository repaymentRepository){
        this.loanRepository = loanRepository;
        this.repaymentRepository = repaymentRepository;
    }

    public List<Repayment> addRepayment(Long loanId, RepaymentRequest repaymentRequest) throws NotFoundException {
        Loan loan = loanRepository.findByLoanId(loanId);
        List<Repayment> repaymentList = repaymentRepository.findAllByLoanId(loanId);
        // Check if the added repayment fulfills a scheduled repayment
        for (Repayment scheduledRepayment : repaymentList) {
            if (scheduledRepayment.getDate().equals(repaymentRequest.getDate())) {
                scheduledRepayment.setState(RepaymentState.PAID);
                break;
            }
        }

        // Check if all scheduled repayments are paid
        boolean allPaid = true;
        for (Repayment scheduledRepayment : repaymentList) {
            if (scheduledRepayment.getState() != RepaymentState.PAID) {
                allPaid = false;
                break;
            }
        }

        // Update loan state to PAID if all scheduled repayments are paid
        if (allPaid) {
            loan.setState(LoanState.PAID);
        }

        loanRepository.save(loan);
        repaymentRepository.saveAll(repaymentList);

        return repaymentList;
    }

}

