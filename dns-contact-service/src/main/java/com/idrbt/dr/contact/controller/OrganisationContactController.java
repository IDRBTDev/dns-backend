package com.idrbt.dr.contact.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.idrbt.dr.contact.model.AdministrativeContact;
import com.idrbt.dr.contact.model.OrganisationContact;
import com.idrbt.dr.contact.repository.AdministrativeContactRepository;
import com.idrbt.dr.contact.repository.OrganisationContactRepository;
import com.idrbt.dr.contact.service.AdministrativeContactService;
import com.idrbt.dr.contact.service.OrganisationContactService;

import java.util.List;

@RestController
@RequestMapping("/dr/organisationContact")
@CrossOrigin(origins = "http://localhost:3000")
public class OrganisationContactController {
    @Autowired
    private OrganisationContactRepository organisationContactRepository;

    @Autowired
    private OrganisationContactService organisationContactService = new OrganisationContactService();

    @GetMapping("/getDetails")
    public OrganisationContact getOrganisationContactDetails(@RequestParam String applicationId) {
        return organisationContactService.getOrganisationContactDetails(applicationId);
    }

    @PostMapping
    public OrganisationContact saveForm(@RequestBody OrganisationContact organisationContact) {
        //System.out.println(login);
        //organisationDetails.setApplicationId("IDRBT24" + String.valueOf((int) ((Math.random() * 900000) + 100000)));
        return organisationContactRepository.save(organisationContact);
    }
}
