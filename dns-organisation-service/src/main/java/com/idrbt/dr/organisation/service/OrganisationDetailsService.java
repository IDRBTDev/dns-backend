package com.idrbt.dr.organisation.service;

import com.idrbt.dr.organisation.dto.OrganisationDetailsDto;

public interface OrganisationDetailsService {
    
	public OrganisationDetailsDto saveOrganisationDetails(OrganisationDetailsDto organisationDetailsDto);
    public OrganisationDetailsDto getOrganisationDetails(String applicationId);
    public OrganisationDetailsDto getOrganisationDetailsByDomainId(Long domainId);
    
}
