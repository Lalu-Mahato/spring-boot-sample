package com.example.springbootsample.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "fieldofficers")
public class Fieldofficer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String foId;

    private String bmId;

    private String foName;

    private String username = "";

    private String password = "";

    private String role = "fieldofficer";

    private String createdBy = "branchmanager";
}
