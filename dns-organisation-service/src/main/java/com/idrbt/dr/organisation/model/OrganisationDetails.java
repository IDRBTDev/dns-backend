package com.idrbt.dr.organisation.model;

import jakarta.persistence.*;

@Entity
@Table(name = "organisation_details")
public class OrganisationDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "organisation_details_id", nullable = false)
    private Long organisationDetailsId;

    @Column(name = "application_id", nullable = true)
    private String applicationId;

    @Column(name = "user_name", nullable = true)
    private String user;

    @Column(name = "user_mail_id", nullable = true)
    private String userMailId;

    @Column(name = "institution_name", nullable = true)
    private String institutionName;

    @Column(name = "pincode", nullable = true)
    private int pincode;

    @Column(name = "city", nullable = true)
    private String city;

    @Column(name = "state", nullable = true)
    private String state;

    @Column(name = "address", nullable = true)
    private String address;

    @Column(name = "std_telephone", nullable = true)
    private String stdTelephone;

    @Column(name = "mobile_number", nullable = true)
    private String mobileNumber;

    @Column(name = "organisation_email", nullable = true)
    private String organisationEmail;

    @Column(name = "license_number", nullable = true)
    private String licenseNumber;

    @Column(name = "license_document", nullable = true)
    private byte[] licenseDocument;

    @Column(name = "organisation_gstin", nullable = true)
    private String organisationGstin;

    @Column(name = "organisation_gstin_document", nullable = true)
    private byte[] organisationGstinDocument;

    @Column(name = "board_resolution", nullable = true)
    private byte[] boardResolution;

    @Column(name = "pan", nullable = true)
    private String pan;

    @Column(name = "pan_document", nullable = true)
    private byte[] panDocument;

    @Column(name = "signature_name", nullable = true)
    private String signatureName;

    @Column(name = "domainId")
    private Long domainId;
}
