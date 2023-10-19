package com.example.springbootsample.dto;

import java.util.Date;

import lombok.Data;

@Data
public class LoanDTO {
    private long accountNumber;
    private Double amountSanctioned;
    private Date disbursalDate;
    private Double rateOfInterest;
    private Date loanStartDate;
    private Date loanEndDate;
    private Integer totalTenure;
}
