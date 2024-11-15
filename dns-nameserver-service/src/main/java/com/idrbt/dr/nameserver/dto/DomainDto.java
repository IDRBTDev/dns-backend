package com.idrbt.dr.nameserver.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DomainDto {

    private Long domainId;

    private String applicationId;

    private String user;

    private String userMailId;

    private String bankName;

    private String domainName;

    private int numberOfYears;

    private int cost;
    
    private String organizationName;
    
    private LocalDateTime registrationDate;
    
    private LocalDateTime renewalDate;
    
    private String status;
    
    private String industry;
    
    private String nsRecordStatus;
    
    private LocalDateTime submissionDate;
    
}
