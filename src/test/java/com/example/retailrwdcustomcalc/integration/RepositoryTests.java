package com.example.retailrwdcustomcalc.integration;

import com.example.retailrwdcustomcalc.RetailRwdCustomCalcApplication;
import com.example.retailrwdcustomcalc.common.TestDataGenerator;
import com.example.retailrwdcustomcalc.entity.Customer;
import com.example.retailrwdcustomcalc.entity.Transaction;
import com.example.retailrwdcustomcalc.repository.CustomerRepository;
import com.example.retailrwdcustomcalc.repository.TransactionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = RetailRwdCustomCalcApplication.class)
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class RepositoryTests {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    private final List<Customer> expected = TestDataGenerator.getCustomers();

    @BeforeEach
    public void setUp() {
        cleanUp();
        customerRepository.saveAll(expected);
    }

    @AfterEach
    public void cleanUp() {
        customerRepository.deleteAll();
        transactionRepository.deleteAll();
    }

    @Test
    public void retrieveAllCustomers() {
        List<Customer> actual = customerRepository.findAll();
        assertEquals(expected.size(), actual.size());
    }

    @Test
    public void retrieveACustomer() {
        Optional<Customer> actual = customerRepository.findById(expected.get(0).getId());
        assertTrue(actual.isPresent());
    }

    @Test
    public void retrieveAllTransactions() {
        List<Transaction> actual = transactionRepository.findAll();
        List<Transaction> expectedTransactions = new ArrayList<>();
        expectedTransactions.addAll(expected.get(0).getTransactions());
        expectedTransactions.addAll(expected.get(1).getTransactions());
        expectedTransactions.addAll(expected.get(2).getTransactions());
        assertEquals(expectedTransactions.size(), actual.size());
    }

}
