package com.aspire.repository;
import com.aspire.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    Loan findByLoanId(Long loanId);
    List<Loan>findAllByCustomer(Integer customer);
}

