package com.example.retailrwdcustomcalc.repository;

import com.example.retailrwdcustomcalc.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Override
    List<Customer> findAll();

    @Override
    Optional<Customer> findById(Long aLong);
}
