package com.idrbt.dr.domain.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idrbt.dr.domain.dto.BillingHistoryDto;
import com.idrbt.dr.domain.service.BillingHistoryService;

@RestController
@RequestMapping("/dr/billingHistory")
public class BillingHistoryController {
	
	@Autowired
	private BillingHistoryService billingHistoryService;
	
	@PostMapping
	public ResponseEntity<BillingHistoryDto> saveBillingHistory(@RequestBody BillingHistoryDto billingHistoryDto){
		BillingHistoryDto savedBillingHistoryDto = billingHistoryService.saveBillingHistory(billingHistoryDto);
		return new ResponseEntity<>(savedBillingHistoryDto, HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<BillingHistoryDto>> getAllBillingHistories(){
		List<BillingHistoryDto> billingHistoryDtoList = billingHistoryService.getBillingHistories();
		return new ResponseEntity<>(billingHistoryDtoList, HttpStatus.OK);
	}

}
