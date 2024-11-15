package com.idrbt.dr.declaration.model;

import jakarta.persistence.*;

import java.util.Arrays;

@Entity
@Table(name = "login")
public class Login {

//    @Column(name = "id", nullable = false)
//    private Long id;

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id", nullable = false)
    private String applicationId;

    @Column(name = "name", nullable = true)
    private String name;

    @Column(name = "father_name", nullable = true)
    private String fatherName;

    @Column(name = "father_age", nullable = true)
    private int age;

    @Column(name = "organisation_name", nullable = true)
    private String organizationName;

    @Column(name = "designation", nullable = true)
    private String designation;

//    @Lob
//    @Column(name = "certificate", nullable = true, columnDefinition = "LONGTEXT")
//    private String certificate;
    @Lob
    @Column(name = "certificate", nullable = true, columnDefinition = "TEXT")
    private String certificate;

    @Column(name = "email_id", nullable = true)
    private String emailId;

    @Column(name = "mobile_no", nullable = true)
    private String mobileNumber;

    @Column(name = "password", nullable = true)
    private String password;

    // Constructors
    public Login() {
    }

    public Login(String name, String fatherName, int age, String organisationName, String designation, String certificate, String emailId, String mobileNo, String password) {
        this.applicationId = "IDRBT24" + String.valueOf((int) ((Math.random() * 900000) + 100000));
        System.out.println(applicationId);
        this.name = name;
        this.fatherName = fatherName;
        this.age = age;
        this.organizationName = organisationName;
        this.designation = designation;
        this.certificate = certificate;
        this.emailId = emailId;
        this.mobileNumber = mobileNo;
        this.password = password;
    }

    // Getters and Setters
//    public Long getId() {
//        return id;
//    }
//
//    public void setApplicationId(Long id) {
//        this.id = id;
//    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int fatherAge) {
        this.age = fatherAge;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organisationName) {
        this.organizationName = organisationName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNo) {
        this.mobileNumber = mobileNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // toString method to help with logging
    @Override
    public String toString() {
        return "User{" +
                "domainRegistryId=" + applicationId +
                ", name='" + name + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", fatherAge=" + age +
                ", organisationName='" + organizationName + '\'' +
                ", designation='" + designation + '\'' +
                ", certificate=" + certificate +
                ", emailId='" + emailId + '\'' +
                ", mobileNo='" + mobileNumber + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
