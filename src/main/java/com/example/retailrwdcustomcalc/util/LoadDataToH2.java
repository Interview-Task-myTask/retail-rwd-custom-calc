package com.example.retailrwdcustomcalc.util;

import com.example.retailrwdcustomcalc.entity.Customer;
import com.example.retailrwdcustomcalc.entity.Transaction;
import com.example.retailrwdcustomcalc.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class LoadDataToH2 {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoadDataToH2.class);

    @Bean
    CommandLineRunner initDatabase(CustomerRepository customerRepository) {
        return args -> {
            LOGGER.info("Preloading {}", customerRepository.save(generateCustomer("Jack")));
            LOGGER.info("Preloading {}", customerRepository.save(generateCustomer("Jill")));
            LOGGER.info("Preloading {}", customerRepository.save(generateCustomer("Bill")));
        };
    }

    private Customer generateCustomer(String customerName) {
        Customer customer = new Customer();
        customer.setName(customerName);
        customer.setTransactions(generateTransactions(customer));
        return customer;
    }

    private List<Transaction> generateTransactions(Customer customer) {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(new BigDecimal((int) (Math.random() * 40) + 51), Date.valueOf(LocalDate.now()), customer));
        transactions.add(new Transaction(new BigDecimal((int) (Math.random() * 100) + 1), Date.valueOf(LocalDate.of(2022, (int) (Math.random() * 10) + 1, (int) (Math.random() * 29) + 1)), customer));
        transactions.add(new Transaction(new BigDecimal((int) (Math.random() * 100) + 1), Date.valueOf(LocalDate.of(2022, (int) (Math.random() * 10) + 1, (int) (Math.random() * 29) + 1)), customer));
        return transactions;
    }
}
