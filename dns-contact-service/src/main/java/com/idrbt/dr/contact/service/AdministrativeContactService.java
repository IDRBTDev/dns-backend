package com.idrbt.dr.contact.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idrbt.dr.contact.model.AdministrativeContact;
import com.idrbt.dr.contact.repository.AdministrativeContactRepository;

@Service
public class AdministrativeContactService {
    @Autowired
    private AdministrativeContactRepository administrativeContactRepository;
    public AdministrativeContact getAdministrativeContactDetails(String applicationId) {
        return administrativeContactRepository.findByApplicationId(applicationId).orElse(null);
    }
}
