package com.idrbt.dr.nameserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.idrbt.dr.nameserver.model.NameServer;

import java.util.List;

public interface NameServerRepository extends JpaRepository<NameServer, Long>{
	
    List<NameServer> findAllByApplicationId(String applicationId);
    List<NameServer> findByDomainId(Long domainId);
    
}
