package com.example.springbootsample.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_details")
public class UserDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String userId;

    private String username;

    private String password;

    private String role;

    private String mobile;

    private String createdBy;

    private Date bmJoindate;

    private String branchName;

    private String foId;

    private String bmId;

    private String region;

    @Builder.Default
    private Integer attemptCount = 0;
}
