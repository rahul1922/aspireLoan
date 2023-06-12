package com.aspire.model;

import com.aspire.enums.RepaymentState;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="repayment")
public class Repayment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="loan_id")
    private Long loanId;

    @Column(name="amount")
    private BigDecimal amount;

    @Column(name = "created_at")
    private LocalDate date;

    @Column(name="repayment_status")
    @Enumerated(EnumType.STRING)
    private RepaymentState state;

    public long getId() {
        return id;
    }

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public RepaymentState getState() {
        return state;
    }

    public void setState(RepaymentState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Repayment{" +
                "id=" + id +
                ", loanId='" + loanId + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", state=" + state +
                '}';
    }

}


