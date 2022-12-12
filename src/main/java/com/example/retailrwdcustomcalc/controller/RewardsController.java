package com.example.retailrwdcustomcalc.controller;

import com.example.retailrwdcustomcalc.entity.Customer;
import com.example.retailrwdcustomcalc.entity.Transaction;
import com.example.retailrwdcustomcalc.exceptionhandlers.CustomerNotFoundException;
import com.example.retailrwdcustomcalc.repository.CustomerRepository;
import com.example.retailrwdcustomcalc.repository.TransactionRepository;
import com.example.retailrwdcustomcalc.util.RewardsCalculatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RewardsController {
    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(RewardsController.class);

    RewardsController(CustomerRepository customerRepo, TransactionRepository transactionRepo) {
        this.customerRepository = customerRepo;
        this.transactionRepository = transactionRepo;
    }

    @GetMapping("/Rewards")
    List<Customer> getAllRewards() {
        LOGGER.info("Getting rewards for all customers");
        List<Customer> customers = customerRepository.findAll();
        RewardsCalculatorUtil.calculateRewardsForAllCustomers(customers);
        return customers;
    }

    @GetMapping("/Rewards/{id}")
    Customer getRewardsForSingleCustomer(@PathVariable Long id) {
        LOGGER.info("Getting rewards for the customer {}", id);
        return customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @GetMapping("/transactions")
    List<Transaction> getAllTransactions() {
        LOGGER.info("Getting all transactions");
        return transactionRepository.findAll();
    }

}
