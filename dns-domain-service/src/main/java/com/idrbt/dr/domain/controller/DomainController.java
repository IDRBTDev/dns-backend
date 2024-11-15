package com.idrbt.dr.domain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.idrbt.dr.domain.dto.DomainDto;
import com.idrbt.dr.domain.repository.DomainRepository;
import com.idrbt.dr.domain.service.DomainService;

import java.util.List;

@RestController
@RequestMapping("/dr/domain")
@CrossOrigin(origins = "http://localhost:3000")
public class DomainController {
	
    @Autowired
    private DomainRepository domainRepository;

    @Autowired
    private DomainService domainService;

    @GetMapping("/getDetails")
    public ResponseEntity<DomainDto> getDomainDetails(@RequestParam String applicationId) {
        DomainDto domainDto = domainService.getDomainDetails(applicationId);
        return new ResponseEntity<>(domainDto, HttpStatus.OK);
    }
    
    @GetMapping("/getDetails/{domainId}")
    public ResponseEntity<DomainDto> getDomainDetails(@PathVariable Long domainId) {
        DomainDto domainDto = domainService.getDomainDetailsById(domainId);
        return new ResponseEntity<>(domainDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DomainDto> saveForm(@RequestBody DomainDto domainDto) {
        //System.out.println(login);
        //organisationDetails.setApplicationId("IDRBT24" + String.valueOf((int) ((Math.random() * 900000) + 100000)));
    	DomainDto savedDomainDto = domainService.saveDomain(domainDto);
        return new ResponseEntity<>(savedDomainDto, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<DomainDto> updateDomain(@RequestParam String applicationId,
    		@RequestBody DomainDto updateDomainDto) {
        System.out.println(applicationId);
        DomainDto updatedDomainDto = domainService.updateDomain(applicationId, updateDomainDto);
        return new ResponseEntity<DomainDto>(updatedDomainDto, HttpStatus.PARTIAL_CONTENT);
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<DomainDto>> getAllDomains(){
    	List<DomainDto> domainList = domainService.getAllDomains();
    	return new ResponseEntity<>(domainList, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{domainId}")
    public ResponseEntity<Boolean> deleteDomain(@PathVariable Long domainId){
    	domainService.deleteDomain(domainId);
		return new ResponseEntity<>(true, HttpStatus.OK);
    }
    
}
