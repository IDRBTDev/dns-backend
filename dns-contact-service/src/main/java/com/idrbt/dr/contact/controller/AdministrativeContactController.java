package com.idrbt.dr.contact.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.idrbt.dr.contact.model.AdministrativeContact;
import com.idrbt.dr.contact.repository.AdministrativeContactRepository;
import com.idrbt.dr.contact.service.AdministrativeContactService;

import java.util.List;

@RestController
@RequestMapping("/dr/administrativeContact")
@CrossOrigin(origins = "http://localhost:3000")
public class AdministrativeContactController {
    @Autowired
    private AdministrativeContactRepository administrativeContactRepository;

    @Autowired
    private AdministrativeContactService administrativeContactService = new AdministrativeContactService();

    @GetMapping("/getDetails")
    public AdministrativeContact getAdministrativeContactDetails(@RequestParam String applicationId) {
        return administrativeContactService.getAdministrativeContactDetails(applicationId);
    }

    @PostMapping
    public AdministrativeContact saveForm(@RequestBody AdministrativeContact administrativeContact) {
        //System.out.println(login);
        //organisationDetails.setApplicationId("IDRBT24" + String.valueOf((int) ((Math.random() * 900000) + 100000)));
        return administrativeContactRepository.save(administrativeContact);
    }
}
