package com.idrbt.dr.domain.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.idrbt.dr.domain.dto.BillingHistoryDto;
import com.idrbt.dr.domain.dto.DomainDto;
import com.idrbt.dr.domain.dto.NameServerDto;
import com.idrbt.dr.domain.dto.OrganisationDetailsDto;
import com.idrbt.dr.domain.exception.ResourceNotFoundException;
import com.idrbt.dr.domain.model.Domain;
import com.idrbt.dr.domain.repository.DomainRepository;
import com.idrbt.dr.domain.service.BillingHistoryService;
import com.idrbt.dr.domain.service.DomainService;

@Service
public class DomainServiceImpl implements DomainService {

	@Autowired
	private DomainRepository domainRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private BillingHistoryService billingHistoryService;

	@Override
	public DomainDto saveDomain(DomainDto domainDto) {
		Domain domain = new Domain();
		mapper.map(domainDto, domain);
		Domain savedDomain = domainRepository.save(domain);
		mapper.map(savedDomain, domainDto);
		return domainDto;
	}

	@Override
	public DomainDto updateDomain(String applicationId, DomainDto updatedDomain) {
		Optional<Domain> existingDomainOptional = domainRepository.findByApplicationId(applicationId);

		if (existingDomainOptional.isPresent()) {
			Domain existingDomain = existingDomainOptional.get();
			// Copy properties from updatedDomain to existingDomain, excluding 'id' and
			// 'applicationId'
			BeanUtils.copyProperties(updatedDomain, existingDomain, "applicationId", "serialNumber");

			// Save the updated entity
			Domain domain = domainRepository.save(existingDomain);

			// TODO: build these variables
			String zoneName = "";
			String domainName = "";
			String nameServer = "";

			if (domain.getStatus().equalsIgnoreCase("Approved")) {
				// TODO: Create the NS record into Bind DNS

				restTemplate.exchange("http://localhost:8081/api/domain/createNSRecord/" + zoneName + "/" + domainName
						+ "/" + nameServer, HttpMethod.POST, null, Void.class);
			}
			DomainDto domainDto = new DomainDto();
			mapper.map(domain, domainDto);
			return domainDto;
		} else {
			throw new ResourceNotFoundException("Domain not found with applicationId: " + applicationId);
		}
	}

	@Override
	public DomainDto getDomainDetails(String applicationId) {
		Domain domain = domainRepository.findByApplicationId(applicationId).orElse(null);
		DomainDto domainDto = new DomainDto();
		mapper.map(domain, domainDto);
		return domainDto;
	}

	@Override
	public DomainDto getDomainDetailsById(Long domainId) {
		Domain domain = domainRepository.findById(domainId).orElse(null);
		DomainDto domainDto = new DomainDto();
		mapper.map(domain, domainDto);

		// get name servers details of domain
		ResponseEntity<List<NameServerDto>> nameServersListResp = restTemplate.exchange(
				"http://localhost:8084/domainregistry/nameServer/getDetails/" + domainId, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<NameServerDto>>() {
				});
		List<NameServerDto> nameServerList = nameServersListResp.getBody();

		// get organisation details of domain
		ResponseEntity<OrganisationDetailsDto> orgDetailsResp = restTemplate.exchange(
				"http://localhost:8082/domainregistry/organisationDetails/getDetails/" + domainId, HttpMethod.GET, null,
				new ParameterizedTypeReference<OrganisationDetailsDto>() {
				});
		OrganisationDetailsDto orgDetails = orgDetailsResp.getBody();
		
		//set billing history of domain
		List<BillingHistoryDto> billingHistoryList = billingHistoryService.getBillingHistoryByDomain(domainId);

		domainDto.setNameServers(nameServerList);
		domainDto.setOrganisationDetails(orgDetails);
		domainDto.setBillingHistory(billingHistoryList);

		return domainDto;
	}

	@Override
	public List<DomainDto> getAllDomains() {
		List<Domain> domainList = domainRepository.findAll();
		List<DomainDto> domainDtoList = domainList.stream()
				.map(domain -> mapper.map(domain, DomainDto.class))
				.collect(Collectors.toList());
		return domainDtoList;
	}

	@Override
	public void deleteDomain(Long id) {
		Optional<Domain> optDomain = domainRepository.findById(id);
		Domain domain = optDomain.get();
		domainRepository.delete(domain);
	}

}
