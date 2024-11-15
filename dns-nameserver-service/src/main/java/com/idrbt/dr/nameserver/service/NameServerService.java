package com.idrbt.dr.nameserver.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idrbt.dr.nameserver.model.NameServer;
import com.idrbt.dr.nameserver.repository.NameServerRepository;

import java.util.List;


@Service
public class NameServerService {
	
    @Autowired
    NameServerRepository nameServerRepository;
    
    public List<NameServer> getNameServers(String applicationId) {
        System.out.println("Fetching NameServers for applicationId: " + applicationId);
        return nameServerRepository.findAllByApplicationId(applicationId);
    }
    
    public List<NameServer> getNameServersByDomainId(Long domainId){
    	return nameServerRepository.findByDomainId(domainId);
    }
    
}
