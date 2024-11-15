package com.idrbt.dr.declaration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DNSDeclarationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(DNSDeclarationServiceApplication.class, args);
    }
}
