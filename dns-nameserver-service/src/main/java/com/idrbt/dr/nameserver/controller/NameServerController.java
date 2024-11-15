package com.idrbt.dr.nameserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.idrbt.dr.nameserver.model.NameServer;
import com.idrbt.dr.nameserver.repository.NameServerRepository;
import com.idrbt.dr.nameserver.service.NameServerService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dr/nameServer")
@CrossOrigin(origins = "http://localhost:3000")
public class NameServerController {
    @Autowired
    private NameServerRepository nameServerRepository;

    @Autowired
    private NameServerService nameServerService = new NameServerService();

    @GetMapping("/getDetails")
    public ResponseEntity<List<NameServer>> getNameServerDetails(@RequestParam String applicationId) {
        System.out.println("Received applicationId: " + applicationId);
        List<NameServer> nameServers = nameServerService.getNameServers(applicationId);
        if (nameServers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Return 404 if not found
        }
        return ResponseEntity.ok(nameServers);
    }
    
    @GetMapping("/getDetails/{domainId}")
    public ResponseEntity<List<NameServer>> getNameServerDetails(@PathVariable Long domainId) {
        System.out.println("Received applicationId: " + domainId);
        List<NameServer> nameServers = nameServerService.getNameServersByDomainId(domainId);
        if (nameServers.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Return 404 if not found
        }
        return ResponseEntity.ok(nameServers);
    }
    
    

    @PostMapping
    public ResponseEntity<String> saveNameServers(@RequestBody Map<String, List<NameServer>> request) {
        List<NameServer> nameServers = request.get("nameServers");

        // Save all name servers in one call for efficiency
        nameServerRepository.saveAll(nameServers);

        return ResponseEntity.ok("Name Servers Saved Successfully");
    }
}
