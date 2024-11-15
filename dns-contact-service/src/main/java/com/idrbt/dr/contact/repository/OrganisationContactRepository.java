package com.idrbt.dr.contact.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.idrbt.dr.contact.model.OrganisationContact;

import java.util.Optional;

public interface OrganisationContactRepository extends JpaRepository<OrganisationContact, Long> {
    Optional<OrganisationContact> findByApplicationId(String applicationId);
}
