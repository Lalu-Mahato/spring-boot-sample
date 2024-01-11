package com.example.springbootsample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootsample.service.BankService;

@RestController
@RequestMapping("/api/v1")
public class BankController {
    @Autowired
    private BankService bankService;

    @GetMapping("/banks")
    public ResponseEntity<Object> findAll() {
        try {
            ResponseEntity<Object> response = bankService.findAll();
            return ResponseEntity.status(response.getStatusCode()).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error:" + e.getMessage());
        }
    }
}
