package com.example.springbootsample.dto;

import lombok.Data;

@Data
public class FieldofficerDTO {
    private String foId;
    private String bmId;
    private String foName;

    public FieldofficerDTO(String foId, String bmId, String foName) {
        this.foId = foId;
        this.bmId = bmId;
        this.foName = foName;
    }
}
