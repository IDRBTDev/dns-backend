package com.idrbt.dr.document.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.idrbt.dr.document.model.OrganisationDocument;
import com.idrbt.dr.document.repository.OrganisationDocumentRepository;

import java.sql.Blob;
import javax.sql.rowset.serial.SerialBlob;

@Service
public class OrganisationDocumentService {

    @Autowired
    private OrganisationDocumentRepository documentRepository;

    public void saveDocuments(MultipartFile[] files, String[] types, String[] values, String applicationId, String user, String userMailId) throws Exception {
        if (files.length != types.length || files.length != values.length) {
            throw new IllegalArgumentException("Mismatch between number of files, types, and values.");
        }

        for (int i = 0; i < files.length; i++) {
            saveDocument(files[i], types[i], values[i], applicationId, user, userMailId);
        }
    }

    public void saveDocument(MultipartFile file, String type, String value, String applicationId, String user, String userMailId) throws Exception {
        OrganisationDocument document = new OrganisationDocument();

        // Set metadata fields
        document.setApplicationId(applicationId);
        document.setUser(user);
        document.setUserMailId(userMailId);

        // Populate document fields based on type
        Blob fileBlob = new SerialBlob(file.getBytes());
        System.out.println(fileBlob);

        switch (type) {
            case "PAN":
                document.setPanNumber(value);
                document.setPanDocument(fileBlob);
                break;
            case "License No":
                document.setLicenseNumber(value);
                document.setLicenseDocument(fileBlob);
                break;
            case "Board Resolution":
                document.setBoardResolutionDocument(fileBlob);
                break;
            case "Organisation GSTIN":
                document.setOrganisationGstinNumber(value);
                document.setOrganisationGstinDocument(fileBlob);
                break;
            default:
                throw new IllegalArgumentException("Invalid document type");
        }
        // Save to database
        documentRepository.save(document);
    }
}