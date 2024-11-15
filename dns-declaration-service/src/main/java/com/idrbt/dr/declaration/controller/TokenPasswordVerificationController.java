package com.idrbt.dr.declaration.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.idrbt.dr.declaration.model.SignRequest;
import com.idrbt.dr.declaration.service.TokenPasswordVerificationService;

import java.util.Map;

@RestController
@RequestMapping("/dr/declaration")
@CrossOrigin(origins = "http://localhost:3000")

public class TokenPasswordVerificationController {
    @PostMapping("/tokenPasswordVerify")
    public ResponseEntity<Map<String, String>> signDataWithToken(
            @RequestBody SignRequest request) {
        TokenPasswordVerificationService tokenPasswordVerificationService = new TokenPasswordVerificationService();
        return tokenPasswordVerificationService.verifyToken(request.getTokenPin(), request.getDataToSign());
//        try {
//            // Load the SunPKCS11 provider using the configuration file
//            Provider pkcs11Provider = Security.getProvider("SunPKCS11");
//            pkcs11Provider = pkcs11Provider.configure("src/main/resources/pkcs11.cfg");
//            Security.addProvider(pkcs11Provider);
//
//            // Initialize the KeyStore with the PKCS#11 provider
//            KeyStore keyStore = KeyStore.getInstance("PKCS11", pkcs11Provider);
//
//            // Load the KeyStore using the provided token PIN
//            char[] tokenPin = request.getTokenPin().toCharArray();
//            keyStore.load(null, tokenPin);
//
//            // Find the first private key entry
//            Enumeration<String> aliases = keyStore.aliases();
//            String alias = null;
//            while (aliases.hasMoreElements()) {
//                alias = aliases.nextElement();
//                if (keyStore.isKeyEntry(alias)) {
//                    break;
//                }
//            }
//
//            // Retrieve the private key from the token
//            PrivateKey privateKey = (PrivateKey) keyStore.getKey(alias, null);
//
//            // Create a Signature instance (e.g., SHA256withRSA)
//            Signature signature = Signature.getInstance("SHA256withRSA");
//
//            // Initialize the Signature object with the private key
//            signature.initSign(privateKey);
//
//            // Update the signature with the raw data (converted from Base64)
//            byte[] dataToSign = Base64.getDecoder().decode(request.getDataToSign());
//            signature.update(dataToSign);
//
//            // Generate the digital signature
//            byte[] digitalSignature = signature.sign();
//
//            // Encode the digital signature to Base64 to return it as a string
//            String encodedSignature = Base64.getEncoder().encodeToString(digitalSignature);
//
//            return ResponseEntity.ok(encodedSignature);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during signing: " + e.getMessage());
//        }
    }
}
