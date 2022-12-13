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
        transactions.add(new Transaction(new BigDecimal(150 ), Date.valueOf(LocalDate.now()), customer));
        transactions.add(new Transaction(new BigDecimal(50 ), Date.valueOf(LocalDate.now()), customer));
        transactions.add(new Transaction(new BigDecimal(30 ), Date.valueOf(LocalDate.now()), customer));
        return transactions;
    }

    public List<Customer> getCustomers() {
        List<Customer> expected = new ArrayList<Customer>();
        expected.add(TestDataGenerator.generateCustomer("King"));
        expected.add(TestDataGenerator.generateCustomer("Queen"));
        expected.add(TestDataGenerator.generateCustomer("Jack"));
        return expected;
    }
}
