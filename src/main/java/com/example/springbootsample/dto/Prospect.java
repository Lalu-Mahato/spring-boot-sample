package com.example.springbootsample.dto;

import lombok.Data;

@Data
public class Prospect {
    private String name;
    private String address;
    private Integer mobileNumber;
    private String alternateMobileNumber;
    private Boolean isGroupHead = true;
    private Boolean isActive = true;
    private Boolean isNpaAcount = false;
    private Integer cifId;
    private String imageUrl = "https://randomuser.me/api/portraits/men/75.jpg";
}
