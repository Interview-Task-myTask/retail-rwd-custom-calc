package com.example.retailrwdcustomcalc.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
public class Transactions {
    private @Id @GeneratedValue Long transactionId;
    private BigDecimal transactionTotal;
    private Date transactionDate;
    private Long customerId;
}
