package com.example.springbootsample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springbootsample.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
