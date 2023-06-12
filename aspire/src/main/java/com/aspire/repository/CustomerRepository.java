package com.aspire.repository;

import com.aspire.model.Customer;
import com.aspire.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  CustomerRepository extends JpaRepository<Customer, Loan> {
    Customer findById(long id);

}
