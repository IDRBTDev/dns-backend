package com.idrbt.dr.contact.model;

import jakarta.persistence.*;

@Entity
@Table(name = "technical_contact")
public class TechnicalContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "serial_number", nullable = false)
    private int serialNumber;

    @Column(name = "application_id", nullable = true)
    private String applicationId;

    @Column(name = "user_name", nullable = true)
    private String user;

    @Column(name = "user_mail_id", nullable = true)
    private String userMailId;

    @Column(name = "contact_person_name", nullable = true)
    private String contactPersonName;

    @Column(name = "designation", nullable = true)
    private String designation;

    @Column(name = "organisation_name", nullable = true)
    private String organisationName;

    @Column(name = "mobile_number", nullable = true)
    private String mobileNumber;

    @Column(name = "std_telephone_number", nullable = true)
    private String stdTelephone;

    @Column(name = "technical_contact_email", nullable = true)
    private String technicalContactEmail;

    @Column(name = "pincode", nullable = true)
    private int pincode;

    @Column(name = "city", nullable = true)
    private String city;

    @Column(name = "state", nullable = true)
    private String state;

    @Column(name = "address_line_1", nullable = true)
    private String addressLine1;

    @Column(name = "address_line_2", nullable = true)
    private String addressLine2;

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
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

    public String getTechnicalContactEmail() {
        return technicalContactEmail;
    }

    public void setTechnicalContactEmail(String technicalContactEmail) {
        this.technicalContactEmail = technicalContactEmail;
    }

    public String getContactPersonName() {
        return contactPersonName;
    }

    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getOrganisationName() {
        return organisationName;
    }

    public void setOrganisationName(String organisationName) {
        this.organisationName = organisationName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getStdTelephone() {
        return stdTelephone;
    }

    public void setStdTelephone(String stdTelephoneNumber) {
        this.stdTelephone = stdTelephoneNumber;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String address_line_2) {
        this.addressLine2 = address_line_2;
    }
}
