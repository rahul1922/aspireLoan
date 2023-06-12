package com.aspire.model;

import jakarta.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "customer_id")
    private long customerid;

    public Customer() {

    }

    public long getId() {
        return id;
    }


    public long getCustomerid() {
        return customerid;
    }

    public void setCustomerid(long customerid) {
        this.customerid = customerid;
    }

    public Customer(Long customerid) {
        this.customerid=customerid;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", customerId=" + customerid + "";
    }

}

