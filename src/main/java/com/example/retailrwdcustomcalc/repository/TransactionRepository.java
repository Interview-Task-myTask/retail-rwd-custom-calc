package com.example.retailrwdcustomcalc.repository;

import com.example.retailrwdcustomcalc.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Override
    List<Transaction> findAll();

    @Override
    Optional<Transaction> findById(Long aLong);
}
