package com.idrbt.dr.domain.service;

import java.util.List;

import com.idrbt.dr.domain.dto.BillingHistoryDto;

public interface BillingHistoryService {
	
      BillingHistoryDto saveBillingHistory(BillingHistoryDto billingHistoryDto);
      List<BillingHistoryDto> getBillingHistories();
      List<BillingHistoryDto> getBillingHistoryByDomain(Long domainId);
	

}
