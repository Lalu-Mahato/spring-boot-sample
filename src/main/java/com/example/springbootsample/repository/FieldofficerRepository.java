package com.example.springbootsample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springbootsample.model.Fieldofficer;

@Repository
public interface FieldofficerRepository extends JpaRepository<Fieldofficer, Integer> {

}
