package com.idrbt.dr.declaration.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.idrbt.dr.declaration.model.Login;
import com.idrbt.dr.declaration.model.SignRequest;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.*;
import javax.security.auth.x500.X500Principal;

@RestController
public class TokenPasswordVerificationService {
    Login login;

    public ResponseEntity<Map<String, String>> verifyToken(String tokenPinString, String dataToSignString) {
        try {
            // Load the SunPKCS11 provider using the configuration file
            Provider pkcs11Provider = Security.getProvider("SunPKCS11");
            pkcs11Provider = pkcs11Provider.configure("src/main/resources/pkcs11.cfg");
            Security.addProvider(pkcs11Provider);

            // Initialize the KeyStore with the PKCS#11 provider
            KeyStore keyStore = KeyStore.getInstance("PKCS11", pkcs11Provider);

            // Load the KeyStore using the provided token PIN
            char[] tokenPin = tokenPinString.toCharArray();
            keyStore.load(null, tokenPin);

            // Find the first private key entry
            Enumeration<String> aliases = keyStore.aliases();
            String alias = null;
            while (aliases.hasMoreElements()) {
                alias = aliases.nextElement();
                if (keyStore.isKeyEntry(alias)) {
                    break;
                }
            }

            // Retrieve the private key from the token
            PrivateKey privateKey = (PrivateKey) keyStore.getKey(alias, null);

            // Retrieve the certificate from the token
            Certificate certificate = keyStore.getCertificate(alias);

            // Cast the certificate to X509Certificate to extract details
            X509Certificate x509Certificate = (X509Certificate) certificate;

            // Extract Subject DN from the certificate
            X500Principal subjectPrincipal = x509Certificate.getSubjectX500Principal();
            String subjectDN = subjectPrincipal.getName();

            // Extract Organization Name (O) and Common Name (CN) from the subject DN
            String organizationName = extractFieldFromDN(subjectDN, "O");
            String commonName = extractFieldFromDN(subjectDN, "CN");
            String mail = new String();
            Collection<List<?>> sanEntries = x509Certificate.getSubjectAlternativeNames();
            if (sanEntries != null) {
                for (List<?> sanEntry : sanEntries) {
                    int nameType = (Integer) sanEntry.get(0);
                    // GeneralName type 1 indicates rfc822Name (email)
                    if (nameType == 1) {
                        mail = (String) sanEntry.get(1);
                    }
                }
            }
            System.out.println(mail);
            // Convert the certificate to Base64
            String encodedCertificate = Base64.getEncoder().encodeToString(x509Certificate.getEncoded());
            byte[] utf8Bytes = encodedCertificate.getBytes();
            String utf8EncodedCertificate = new String(utf8Bytes, StandardCharsets.UTF_8);

            // Create a Signature instance
            Signature signature = Signature.getInstance("SHA256withRSA", pkcs11Provider);

            // Initialize the Signature object with the private key
            signature.initSign(privateKey);

            // Update the signature with the raw data (converted from Base64)
            byte[] dataToSign = Base64.getDecoder().decode(dataToSignString);
            signature.update(dataToSign);

            // Generate the digital signature
            byte[] digitalSignature = signature.sign();

            // Encode the digital signature to Base64
            String encodedSignature = Base64.getEncoder().encodeToString(digitalSignature);
            login = new Login();
            //login.setCertificate(encodedCertificate.getBytes(StandardCharsets.UTF_8));
            //byte[] encodedCertificateBytes = encodedCertificate.getBytes(StandardCharsets.UTF_8);
            System.out.println(encodedCertificate);
            // Prepare the JSON response
            Map<String, String> response = new HashMap<>();
            response.put("digitalSignature", encodedSignature);
            response.put("certificate", encodedCertificate);
            response.put("commonName", commonName);
            response.put("organizationName", organizationName);
            response.put("mail", mail);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Error during signing: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    public String convertToPem(String cert) {
        String pemCert = "";
        for (int i = 0; i < cert.length(); i += 64) {
            pemCert += cert.substring(i, Math.min(i + 64, cert.length())) + "\n";
        }
        return pemCert;
    }

    // Helper method to extract specific fields (like CN, O) from the DN string
    private String extractFieldFromDN(String dn, String field) {
        String[] dnFields = dn.split(",");
        for (String dnField : dnFields) {
            String[] keyValue = dnField.trim().split("=");
            if (keyValue.length == 2 && keyValue[0].equalsIgnoreCase(field)) {
                return keyValue[1];
            }
        }
        return null;
    }
}
