package com.idrbt.dr.domain.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idrbt.dr.domain.dto.BillingHistoryDto;
import com.idrbt.dr.domain.model.BillingHistory;
import com.idrbt.dr.domain.repository.BillingHistoryRepository;
import com.idrbt.dr.domain.service.BillingHistoryService;

@Service
public class BillingHistoryServiceImpl implements BillingHistoryService {
	
	@Autowired
	private BillingHistoryRepository billingHistoryRepository;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public BillingHistoryDto saveBillingHistory(BillingHistoryDto billingHistoryDto) {
		BillingHistory billingHistory = new BillingHistory();
		mapper.map(billingHistoryDto, billingHistory);
		BillingHistory savedBillingHistory = billingHistoryRepository.save(billingHistory);
		mapper.map(savedBillingHistory, billingHistoryDto);
		return billingHistoryDto;
	}

	@Override
	public List<BillingHistoryDto> getBillingHistories() {
		List<BillingHistory> billingHistoryList = billingHistoryRepository.findAll();
		List<BillingHistoryDto> billingHistoryDtoList = billingHistoryList.stream()
				.map(entity -> mapper.map(entity, BillingHistoryDto.class))
		.collect(Collectors.toList());
		return billingHistoryDtoList;
	}

	@Override
	public List<BillingHistoryDto> getBillingHistoryByDomain(Long domainId) {
		List<BillingHistory> billingHistoryList = billingHistoryRepository.findByDomainId(domainId);
		List<BillingHistoryDto> billingHistoryDtoList = billingHistoryList.stream()
				.map(entity -> mapper.map(entity, BillingHistoryDto.class))
		.collect(Collectors.toList());
		return billingHistoryDtoList;
	}

}
