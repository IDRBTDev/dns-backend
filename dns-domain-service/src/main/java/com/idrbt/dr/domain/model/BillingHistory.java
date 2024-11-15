package com.idrbt.dr.domain.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "billing_history")
public class BillingHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "billingId")
	private Long billingId;
	@Column(name = "invoiceNumber")
	private String invoiceNumber;
	@Column(name = "amount")
	private String amount;
	@Column(name = "invoiceDate")
	private LocalDateTime invoiceDate;
	@Column(name = "status")
	private String status;
	@Column(name = "domainId")
	private Long domainId;
	
}
