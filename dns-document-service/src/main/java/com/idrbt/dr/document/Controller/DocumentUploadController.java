package com.idrbt.dr.document.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.idrbt.dr.document.service.OrganisationDocumentService;

@RestController
@RequestMapping("/dr/documents")
@CrossOrigin(origins = "http://localhost:3000")
public class DocumentUploadController {

    @Autowired
    private OrganisationDocumentService documentService;

    @PostMapping("/documentUpload")
    public ResponseEntity<?> uploadDocuments(
            @RequestParam("files") MultipartFile[] files,
            @RequestParam("types") String[] types,
            @RequestParam("values") String[] values,
            @RequestParam("applicationId") String applicationId,
            @RequestParam("user") String user,
            @RequestParam("userMailId") String userMailId) {

        if (files.length != types.length || files.length != values.length) {
            return ResponseEntity.badRequest().body("Mismatch between number of files, types, and values.");
        }

        try {
            for (int i = 0; i < files.length; i++) {
                documentService.saveDocument(files[i], types[i], values[i], applicationId, user, userMailId);
            }
            return ResponseEntity.ok("Documents uploaded successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error uploading documents: " + e.getMessage());
        }
    }
}