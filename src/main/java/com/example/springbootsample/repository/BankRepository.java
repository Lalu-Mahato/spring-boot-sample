package com.example.springbootsample.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springbootsample.model.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, UUID> {

    Optional<Bank> FindByCode(Integer code);

}
