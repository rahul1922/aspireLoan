package com.aspire.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class LoanRequest {
    private BigDecimal amount;
    private int term;
    private LocalDate date;

    // Getters and setters

    // Constructors
}

