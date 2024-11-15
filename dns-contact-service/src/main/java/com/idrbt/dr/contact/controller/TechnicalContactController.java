package com.idrbt.dr.contact.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.idrbt.dr.contact.model.OrganisationContact;
import com.idrbt.dr.contact.model.TechnicalContact;
import com.idrbt.dr.contact.repository.OrganisationContactRepository;
import com.idrbt.dr.contact.repository.TechnicalContactRepository;
import com.idrbt.dr.contact.service.OrganisationContactService;
import com.idrbt.dr.contact.service.TechnicalContactService;

import java.util.List;

@RestController
@RequestMapping("/dr/technicalContact")
@CrossOrigin(origins = "http://localhost:3000")
public class TechnicalContactController {
    @Autowired
    private TechnicalContactRepository technicalContactRepository;

    @Autowired
    private TechnicalContactService technicalContactService = new TechnicalContactService();

    @GetMapping("/getDetails")
    public TechnicalContact getTechnicalContactDetails(@RequestParam String applicationId) {
        return technicalContactService.getTechnicalContactDetails(applicationId);
    }

    @PostMapping
    public TechnicalContact saveForm(@RequestBody TechnicalContact technicalContact) {
        //System.out.println(login);
        //organisationDetails.setApplicationId("IDRBT24" + String.valueOf((int) ((Math.random() * 900000) + 100000)));
        return technicalContactRepository.save(technicalContact);
    }
}
