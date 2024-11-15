package com.idrbt.dr.document;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DNSDocumentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DNSDocumentServiceApplication.class, args);
    }

}
