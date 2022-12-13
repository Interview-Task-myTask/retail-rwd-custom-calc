package com.example.retailrwdcustomcalc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal transactionTotal;
    private Date transactionDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private Customer customer;

    public Transaction(BigDecimal transactionTotal, Date transactionDate, Customer customer) {
        this.transactionTotal = transactionTotal;
        this.transactionDate = transactionDate;
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", transactionTotal=" + transactionTotal +
                ", transactionDate=" + transactionDate +
                '}';
    }
}
