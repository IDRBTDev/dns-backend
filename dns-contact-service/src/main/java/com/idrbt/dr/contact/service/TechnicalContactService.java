package com.idrbt.dr.contact.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idrbt.dr.contact.model.OrganisationContact;
import com.idrbt.dr.contact.model.TechnicalContact;
import com.idrbt.dr.contact.repository.AdministrativeContactRepository;
import com.idrbt.dr.contact.repository.TechnicalContactRepository;

@Service
public class TechnicalContactService {

    @Autowired
    private TechnicalContactRepository technicalContactRepository;
    public TechnicalContact getTechnicalContactDetails(String applicationId) {
        return technicalContactRepository.findByApplicationId(applicationId).orElse(null);
    }
}
