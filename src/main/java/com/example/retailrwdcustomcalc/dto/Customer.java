package com.example.retailrwdcustomcalc.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Customer {
    private @Id @GeneratedValue Long id;
    private String name;
    private Long rewards;
}
