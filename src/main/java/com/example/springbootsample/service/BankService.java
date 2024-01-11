package com.example.springbootsample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.springbootsample.dto.BankDTO;
import com.example.springbootsample.model.Bank;
import com.example.springbootsample.repository.BankRepository;

@Service
public class BankService {
    @Autowired
    private BankRepository bankRepository;

    public ResponseEntity<Object> findAll() {
        List<Bank> banks = bankRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(banks);
    }

    public ResponseEntity<Object> create(BankDTO bankDTO) {
        Bank bank = new Bank();
        bank.setCode(bankDTO.getCode());
        bank.setName(bankDTO.getName());

        Bank response = bankRepository.save(bank);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
