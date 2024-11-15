package com.idrbt.dr.domain.dto;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillingHistoryDto {

	private Long billingId;
	private String invoiceNumber;
	private String amount;
	private LocalDateTime invoiceDate;
	private String status;
	
}
