package com.idrbt.dr.domain;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class DNSDomainServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DNSDomainServiceApplication.class, args);
	}
	
	//@LoadBalanced
	@Bean
	public RestTemplate createRestTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public ModelMapper getMapper() {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return mapper;
	}

}
