package com.idrbt.dr.organisation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.idrbt.dr.organisation.model.OrganisationDetails;

import java.util.Optional;

public interface OrganisationDetailsRepository extends JpaRepository<OrganisationDetails, Long> {
	
    Optional<OrganisationDetails> findByApplicationId(String applicationId);
    OrganisationDetails findByDomainId(Long domainId);
}
