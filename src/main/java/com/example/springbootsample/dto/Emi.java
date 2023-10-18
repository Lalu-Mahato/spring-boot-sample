package com.example.springbootsample.dto;

import java.util.Date;

import lombok.Data;

@Data
public class Emi {
    private Integer currentTenure;
    private Integer residualTenure;
    private Double totalEmiAmount;
    private Double principalEmiAmount;
    private Double interestEmiAmount;
    private Date emiDueDate;
    private String emiStatus = "pending";
    private Double totalOutstanding;
    private Double principalOutstanding;
    private Double interestOutstanding;
    private Double arrearAmount;
    private Double principalArrearAmount;
    private Double interestArrearAmount;
    private Double otherCharges;
    private Double totalCollectionAmount;
    private Integer unpaidInstallment;
    private Integer paidInstallment;
    private Double lastPaidAmount;
    private Date lastRepaymentDate;
    private Integer dpdInDays;
}
