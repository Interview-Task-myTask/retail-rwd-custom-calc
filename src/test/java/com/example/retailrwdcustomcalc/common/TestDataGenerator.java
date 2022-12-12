package com.example.retailrwdcustomcalc.common;

import com.example.retailrwdcustomcalc.entity.Customer;
import com.example.retailrwdcustomcalc.entity.Transaction;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class TestDataGenerator {

    public Customer generateCustomer(String customerName) {
        Customer customer = new Customer();
        customer.setName(customerName);
        customer.setTransactions(generateTransactions(customer));
        return customer;
    }

    public List<Transaction> generateTransactions(Customer customer) {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(new BigDecimal((int) (Math.random() * 40) + 51), Date.valueOf(LocalDate.now()), customer));
        transactions.add(new Transaction(new BigDecimal((int) (Math.random() * 100) + 1), Date.valueOf(LocalDate.of(2022, (int) (Math.random() * 10) + 1, (int) (Math.random() * 29) + 1)), customer));
        transactions.add(new Transaction(new BigDecimal((int) (Math.random() * 100) + 1), Date.valueOf(LocalDate.of(2022, (int) (Math.random() * 10) + 1, (int) (Math.random() * 29) + 1)), customer));
        return transactions;
    }
}
