package com.example.retailrwdcustomcalc.util;

import com.example.retailrwdcustomcalc.common.TestDataGenerator;
import com.example.retailrwdcustomcalc.entity.Customer;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RewardsCalculatorUtilTest {

    @Test
    public void calculateRewards_Success() {
        List<Customer> customers = TestDataGenerator.getCustomers();
        RewardsCalculatorUtil.calculateRewardsForAllCustomers(customers);
        customers.forEach(customer -> {
            assertTrue(customer.getRewards() > 0);
            assertEquals(customer.getRewards(),310);
        });
    }

    @Test
    public void calculateRewardsForCustomer_Success() {
        List<Customer> customers = TestDataGenerator.getCustomers();
        RewardsCalculatorUtil.calculateRewardsForCustomer(customers.get(0));
        assertEquals(customers.get(0).getRewards(), 310);
    }
}
