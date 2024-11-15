package com.idrbt.dr.domain.service;

import java.util.List;

import com.idrbt.dr.domain.dto.DomainDto;

public interface DomainService {
	
	 public DomainDto getDomainDetails(String applicationId);
	 public List<DomainDto> getAllDomains();
	 public DomainDto updateDomain(String applicationId, DomainDto updatedDomainDto);
	 public DomainDto saveDomain(DomainDto domain);
	 public void deleteDomain(Long id);
	 public DomainDto getDomainDetailsById(Long domainId);

}
