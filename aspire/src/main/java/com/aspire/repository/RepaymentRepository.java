package com.aspire.repository;
import com.aspire.model.Repayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepaymentRepository extends JpaRepository<Repayment, Long> {
    List<Repayment> findAllById(Long loanId);
    List<Repayment> findAllByLoanId(Long loanId);
}

