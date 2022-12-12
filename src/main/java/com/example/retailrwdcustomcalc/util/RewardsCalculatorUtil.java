package com.example.retailrwdcustomcalc.util;

import com.example.retailrwdcustomcalc.entity.Customer;
import lombok.experimental.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.List;

@UtilityClass
public class RewardsCalculatorUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(RewardsCalculatorUtil.class);

    public void calculateRewardsForAllCustomers(List<Customer> customers) {
        LOGGER.info("Calculating rewards for all customers");
        customers.forEach(RewardsCalculatorUtil::calculateRewardsForCustomer);
    }

    private void calculateRewardsForCustomer(Customer customer) {
        LOGGER.info("Calculating totalTransactions amount for {}", customer.getId());
        LOGGER.debug("All customer transactions : {}", customer.getTransactions());

        int totalTransaction = customer.getTransactions().stream().filter(transaction -> {
            Calendar currentDateBefore3Months = Calendar.getInstance();
            currentDateBefore3Months.add(Calendar.MONTH, -3);
            LOGGER.debug("Calculating transactions after date {}", currentDateBefore3Months.getTime());
            return currentDateBefore3Months.getTime().before(transaction.getTransactionDate());
        }).mapToInt(transaction -> transaction.getTransactionTotal().intValue()).sum();

        LOGGER.debug("Total transaction amount is {}", totalTransaction);

        int totalRewards = 0;
        if (totalTransaction - 100 > 0) {
            totalRewards = 50 + ((totalTransaction - 100) * 2);
        } else if (totalTransaction - 50 > 0) {
            totalRewards = totalTransaction - 50;
        }

        LOGGER.info("Total rewards points are {}", totalRewards);
        customer.setRewards(totalRewards);
    }

}
