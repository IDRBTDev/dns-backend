package com.idrbt.dr.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.idrbt.dr.domain.model.BillingHistory;

public interface BillingHistoryRepository extends JpaRepository<BillingHistory, Long> {
	
	List<BillingHistory> findByDomainId(Long domainId);

}
