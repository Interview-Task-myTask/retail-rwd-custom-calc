package com.example.retailrwdcustomcalc.controller;

import com.example.retailrwdcustomcalc.common.TestDataGenerator;
import com.example.retailrwdcustomcalc.entity.Customer;
import com.example.retailrwdcustomcalc.entity.Transaction;
import com.example.retailrwdcustomcalc.exceptionhandlers.CustomerNotFoundException;
import com.example.retailrwdcustomcalc.repository.CustomerRepository;
import com.example.retailrwdcustomcalc.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RewardsControllerTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private RewardsController controller;

    @Test
    public void getRewardsForAllCustomers_Success() {
        List<Customer> expected = TestDataGenerator.getCustomers();
        when(customerRepository.findAll()).thenReturn(expected);
        List<Customer> actual = controller.getAllRewards();
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    @Test
    public void getRewardsForAllCustomers_Fail() {
        List<Customer> expected = TestDataGenerator.getCustomers();
        when(customerRepository.findAll()).thenReturn(new ArrayList<>());
        List<Customer> actual = controller.getAllRewards();
        assertNotEquals(expected.size(), actual.size());
    }

    @Test
    public void getRewardsForSingleCustomers_Success() {
        Customer expected = TestDataGenerator.getCustomers().get(0);
        when(customerRepository.findById(expected.getId())).thenReturn(Optional.of(expected));
        Customer actual = controller.getRewardsForSingleCustomer(expected.getId());
        assertEquals(expected, actual);
    }

    @Test
    public void getRewardsForSingleCustomers_Fail() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(CustomerNotFoundException.class, () -> controller.getRewardsForSingleCustomer(1L));
    }

    @Test
    public void getRewardsForTransactions_Success() {
        List<Transaction> expected = TestDataGenerator.generateCustomer("King").getTransactions();
        when(transactionRepository.findAll()).thenReturn(expected);
        List<Transaction> actual = controller.getAllTransactions();
        assertEquals(expected, actual);
    }

}
