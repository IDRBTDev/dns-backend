package com.idrbt.dr.organisation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.idrbt.dr.organisation.dto.OrganisationDetailsDto;
import com.idrbt.dr.organisation.model.OrganisationDetails;
import com.idrbt.dr.organisation.repository.OrganisationDetailsRepository;
import com.idrbt.dr.organisation.service.OrganisationDetailsService;

import java.io.IOException;

@RestController
@RequestMapping("/dr/organisationDetails")
@CrossOrigin(origins = "http://localhost:3000")
public class OrganisationDetailsFormController {

    @Autowired
    private OrganisationDetailsRepository organisationDetailsRepository;

    @Autowired
    private OrganisationDetailsService organisationDetailsServie;

    @GetMapping("/getDetails")
    public ResponseEntity<OrganisationDetailsDto> getOrganisationDetails(@RequestParam String applicationId) {
        OrganisationDetailsDto orgDto = organisationDetailsServie.getOrganisationDetails(applicationId);
        return ResponseEntity.ok(orgDto);
    }
    
    @GetMapping("/getDetails/{domainId}")
    public ResponseEntity<OrganisationDetailsDto> getOrganisationDetails(@PathVariable Long domainId) {
        OrganisationDetailsDto orgDto = organisationDetailsServie.getOrganisationDetailsByDomainId(domainId);
        return ResponseEntity.ok(orgDto);
    }
    
    @PostMapping
    public ResponseEntity<OrganisationDetailsDto> saveForm(@RequestBody OrganisationDetailsDto organisationDetailsDto) throws IOException {
        //organisationDetails.setApplicationId("IDRBT24" + String.valueOf((int) ((Math.random() * 900000) + 100000)));
    	 OrganisationDetailsDto savedOrgDto =  organisationDetailsServie.saveOrganisationDetails(organisationDetailsDto);
    	 return new ResponseEntity<>(savedOrgDto, HttpStatus.CREATED);
    }
    
}
