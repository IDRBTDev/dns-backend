package com.idrbt.dr.document.model;

import jakarta.persistence.*;

import java.sql.Blob;

@Entity
@Table(name = "organisation_documents")
public class OrganisationDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer serialNumber;

    @Column(name = "application_id", nullable = true)
    private String applicationId;

    @Column(name = "user_name", nullable = true)
    private String user;

    @Column(name = "user_mail_id", nullable = true)
    private String userMailId;

    @Column(name="pan_number")
    private String panNumber;

    @Lob
    @Column(name = "pan_document")
    private Blob panDocument;

    @Column(name="license_number")
    private String licenseNumber;

    @Lob
    @Column(name = "license_document")
    private Blob licenseDocument;

    @Lob
    @Column(name = "board_resolution_document")
    private Blob boardResolutionDocument;

    @Column(name = "organisation_gstin_number")
    private String organisationGstinNumber;

    @Lob
    @Column(name = "organisation_gstin_document")
    private Blob organisationGstinDocument;

    // Getters and Setters for all fields
    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUserMailId() {
        return userMailId;
    }

    public void setUserMailId(String userMailId) {
        this.userMailId = userMailId;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public Blob getPanDocument() {
        return panDocument;
    }

    public void setPanDocument(Blob panDocument) {
        this.panDocument = panDocument;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public Blob getLicenseDocument() {
        return licenseDocument;
    }

    public void setLicenseDocument(Blob licenseDocument) {
        this.licenseDocument = licenseDocument;
    }

    public Blob getBoardResolutionDocument() {
        return boardResolutionDocument;
    }

    public void setBoardResolutionDocument(Blob boardResolutionDocument) {
        this.boardResolutionDocument = boardResolutionDocument;
    }

    public String getOrganisationGstinNumber() {
        return organisationGstinNumber;
    }

    public void setOrganisationGstinNumber(String organisationGstinNumber) {
        this.organisationGstinNumber = organisationGstinNumber;
    }

    public Blob getOrganisationGstinDocument() {
        return organisationGstinDocument;
    }

    public void setOrganisationGstinDocument(Blob organisationGstinDocument) {
        this.organisationGstinDocument = organisationGstinDocument;
    }
}