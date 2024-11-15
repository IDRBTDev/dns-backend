package com.idrbt.dr.domain.service;

public interface DomainRegistrationService {

	public void createNSRecord(String zoneName, String domainName, String nameServer) ;
}
