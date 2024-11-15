package com.idrbt.dr.domain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idrbt.dr.domain.service.DomainRegistrationService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/dr/domainRegistration")
@Slf4j
@CrossOrigin("http://localhost:4200")
public class DomainRegistrationController {

	@Autowired
	private DomainRegistrationService domainRegistrationService;

	@PostMapping("/createNSRecord")
	public ResponseEntity<Boolean> createNSRecord(
			@PathVariable String zoneName,
			@PathVariable String domainName,
			@PathVariable String nameServer) {
		try {
			log.info("DomainRegistrationController.createNSRecord() ENTERED");

			// Step 1: Specify the DNS zone and the domain name for which you want to add
			// the NS record.
//        	String zoneName = "mynewdomain.com."; // The DNS zone (e.g., "mynewdomain.com.")
//        	String domainName = "db.mynewdomain.com."; // The subdomain for the NS record (e.g., "db.mynewdomain.com." for the zone)
//        	String nameServer = "ns6.mynewdomain.com."; // The target name server for this NS record

			log.info("DomainRegistrationController.createNSRecord() starting....");
			domainRegistrationService.createNSRecord(zoneName, domainName, nameServer);
			log.info("DomainRegistrationController.createNSRecord() completed....");

			return ResponseEntity.ok(true);

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Error occurred while creating NS record", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
		}
	}

}
