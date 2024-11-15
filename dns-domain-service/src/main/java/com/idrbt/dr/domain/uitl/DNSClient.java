package com.idrbt.dr.domain.uitl;

import java.net.InetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.xbill.DNS.DClass;
import org.xbill.DNS.Message;
import org.xbill.DNS.NSRecord;
import org.xbill.DNS.Name;
import org.xbill.DNS.Rcode;
import org.xbill.DNS.SimpleResolver;
import org.xbill.DNS.Update;

import lombok.extern.slf4j.Slf4j;

/**
 * DNSClient class provides methods for interacting with a DNS server, specifically for creating 
 * NS (Name Server) records in a specified DNS zone. It uses Spring's Environment for configurable 
 * properties and a BIND DNS server IP address.
 * 
 */
@Component
@Slf4j
public class DNSClient {

	@Autowired
	private Environment environment;
	
    public void createNSRecord(String zoneName, String domainName, String nameServer) {
        log.info("DNSClient.createNSRecord() Entered....");
        try {
        
        	// Specify the DNS server address to connect to (BIND server's IP address).
            String dnsBindServerIpAddress = environment.getProperty("dns.bind.server.ip-address");
            
            log.info("DNSClient.createNSRecord() : dnsBindServerIpAddress : " + dnsBindServerIpAddress);
            
            // Set the TTL and DNS class
            long ttl = 3600; // Time to Live in seconds (1 hour)
            int dclass = DClass.IN; // Internet class

            // Create a Name object for the zone and the NS record
            Name zone = Name.fromString(zoneName);
            Name targetDomain = Name.fromString(domainName);
            Name targetNS = Name.fromString(nameServer);
            
            log.info("DNSClient.createNSRecord() is under execution...");
            // Create the NS record with the provided data
            InetAddress inetAddress = InetAddress.getByName(nameServer);
            
            //ARecord aRecord = new ARecord(targetDomain, DClass.IN, ttl, inetAddress);
            NSRecord nsRecord = new NSRecord(targetDomain, dclass, ttl, targetNS);

            // Prepare the update
            Update update = new Update(zone);
            update.add(nsRecord);
            log.info("DNSClient.createNSRecord() - updating the NS record in zone");

            // Create a SimpleResolver instance pointing to the DNS server
            SimpleResolver resolver = new SimpleResolver(dnsBindServerIpAddress);
            log.info("DNSClient.createNSRecord() resolver pointing to DNS Server");

            // Set the resolver to use TCP (often preferred for DNS updates)
            resolver.setTCP(true);

            // Apply TSIG for secure updates
            //TSIG tsig = new TSIG("hmac-sha256", keyName, keySecret);
            //update.setTSIG(tsig);

            // Send the update request
            Message response = resolver.send(update);
            log.info("DNSClient.createNSRecord() sending update request");

            // Check the response code
            int responseCode = response.getRcode();
            log.info("DNSClient.createNSRecord() " + responseCode);
            if (responseCode == Rcode.NOERROR) {
                System.out.println("DNSClient.createNSRecord(): NS record added successfully.");
            } else {
                System.out.println("DNSClient.createNSRecord(): Failed to add NS record. Response code :  "
                        + Rcode.string(responseCode));
            }
            log.info("DNSClient.createNSRecord() executed successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
