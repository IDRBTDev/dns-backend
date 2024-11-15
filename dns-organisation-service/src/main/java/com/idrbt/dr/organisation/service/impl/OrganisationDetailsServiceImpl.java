package com.idrbt.dr.organisation.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idrbt.dr.organisation.dto.OrganisationDetailsDto;
import com.idrbt.dr.organisation.model.OrganisationDetails;
import com.idrbt.dr.organisation.repository.OrganisationDetailsRepository;
import com.idrbt.dr.organisation.service.OrganisationDetailsService;

@Service
public class OrganisationDetailsServiceImpl implements OrganisationDetailsService {
	
	@Autowired
    private OrganisationDetailsRepository organisationDetailsRepository;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public OrganisationDetailsDto saveOrganisationDetails(OrganisationDetailsDto organisationDetailsDto) {
		OrganisationDetails organisationDetails = new OrganisationDetails();
		mapper.map(organisationDetailsDto, organisationDetails);
		OrganisationDetails savedOrgDetails = organisationDetailsRepository.save(organisationDetails);
		mapper.map(savedOrgDetails, organisationDetailsDto);
		return organisationDetailsDto;
	}
	
	public OrganisationDetailsDto getOrganisationDetails(String applicationId) {
        OrganisationDetails orgDetails = organisationDetailsRepository.findByApplicationId(applicationId).orElse(null);
        OrganisationDetailsDto orgDetailsDto = new OrganisationDetailsDto();
        mapper.map(orgDetails, orgDetailsDto);
        return orgDetailsDto;
    }
    
    public OrganisationDetailsDto getOrganisationDetailsByDomainId(Long domainId) {
    	OrganisationDetails orgDetails = organisationDetailsRepository.findByDomainId(domainId);
        OrganisationDetailsDto orgDetailsDto = new OrganisationDetailsDto();
        mapper.map(orgDetails, orgDetailsDto);
        return orgDetailsDto;  
    }

}
