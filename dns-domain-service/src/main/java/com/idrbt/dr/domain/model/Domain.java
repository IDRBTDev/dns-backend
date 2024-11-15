package com.idrbt.dr.domain.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "domain")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Domain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "domainId", nullable = false)
    private Long domainId;

    @Column(name = "application_id", nullable = true)
    private String applicationId;

    @Column(name = "user_name", nullable = true)
    private String user;

    @Column(name = "user_mail_id", nullable = true)
    private String userMailId;

    @Column(name = "bank_name", nullable = true)
    private String bankName;

    @Column(name = "domain_name", nullable = true)
    private String domainName;

    @Column(name = "number_of_years", nullable = true)
    private int numberOfYears;

    @Column(name = "cost", nullable = true)
    private int cost;
    
    @Column(name = "organizationName", nullable = true)
    private String organizationName;
    
    @Column(name = "registrationDate", nullable = true)
    private LocalDateTime registrationDate;
    
    @Column(name = "renewalDate", nullable = true)
    private LocalDateTime renewalDate;
    
    @Column(name = "status", nullable = true)
    private String status;
    
    @Column(name = "industry", nullable = true)
    private String industry;
    
    @Column(name = "nsRecordStatus", nullable = true)
    private String nsRecordStatus;
    
    @Column(name = "submissionDate", nullable = true)
    private LocalDateTime submissionDate;
    
    @Column(name = "nameServerIds")
    private String nameServerIds;
    
    @Column(name = "organisationId")
    private Long organisationId;
    
}
