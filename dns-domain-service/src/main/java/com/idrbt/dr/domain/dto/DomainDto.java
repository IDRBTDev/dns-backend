package com.idrbt.dr.domain.dto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
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
    
    private String nameServerIds;
    
    private Long organisationId;
    
    private List<NameServerDto> nameServers;
    
    private OrganisationDetailsDto organisationDetails;
    
    private List<BillingHistoryDto> billingHistory;
    
}
