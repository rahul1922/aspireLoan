package com.aspire.model;
import com.aspire.enums.LoanState;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="loans")
public class Loan {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="loan_id")
    private Long loanId;

    private BigDecimal amount;

    @Column(name = "customer_id")
    private Integer customer;

    private int term;

    @Column(name="created_at")
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private LoanState state;

}

