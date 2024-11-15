package com.idrbt.dr.organisation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganisationDetailsDto {
   
    private Long organisationDetailsId;

    private String applicationId;

    private String user;

    private String userMailId;

    private String institutionName;

    private int pincode;

    private String city;

    private String state;

    private String address;

    private String stdTelephone;

    private String mobileNumber;

    private String organisationEmail;

    private String licenseNumber;

    private byte[] licenseDocument;

    private String organisationGstin;

    private byte[] organisationGstinDocument;

    private byte[] boardResolution;

    private String pan;

    private byte[] panDocument;

    private String signatureName;

    private Long domainId;
}
