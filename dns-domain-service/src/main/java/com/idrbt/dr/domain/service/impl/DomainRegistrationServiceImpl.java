package com.idrbt.dr.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idrbt.dr.domain.service.DomainRegistrationService;
import com.idrbt.dr.domain.uitl.DNSClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DomainRegistrationServiceImpl implements DomainRegistrationService {

	@Autowired
	private DNSClient dnsClient; // DNS Client to communicate with BIND DNS Server

	@Override
	public void createNSRecord(String zoneName, String domainName, String nameServer) {
		try {
			// Send DNS request to the DNS for Saving the NS Record
			dnsClient.createNSRecord(zoneName, domainName, nameServer);
			log.info("createNSRecord() Domain registration sucess....");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("createNSRecord() Domain registration failed....");
		}
	}
	   

}
