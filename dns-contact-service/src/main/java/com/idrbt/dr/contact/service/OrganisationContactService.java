package com.idrbt.dr.contact.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idrbt.dr.contact.model.OrganisationContact;
import com.idrbt.dr.contact.repository.OrganisationContactRepository;

@Service
public class OrganisationContactService {

    @Autowired
    private OrganisationContactRepository organisationContactRepository;
    public OrganisationContact getOrganisationContactDetails(String applicationId) {
        return organisationContactRepository.findByApplicationId(applicationId).orElse(null);
    }
}
