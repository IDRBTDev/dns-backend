package com.idrbt.dr.document.dto;

import org.springframework.web.multipart.MultipartFile;

import com.idrbt.dr.document.model.OrganisationDocument;

import java.util.List;

public class DocumentUploadRequest {
    private String applicationId;
    private String user;
    private String userMailId;
    private List<OrganisationDocument> documents; // This should match your frontend structure

    // Getters and Setters

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

    public List<OrganisationDocument> getDocuments() {
        return documents;
    }

    public void setDocuments(List<OrganisationDocument> documents) {
        this.documents = documents;
    }
}

