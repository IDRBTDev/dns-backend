package com.idrbt.dr.nameserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NameServerDto {
	
    private Long nameServerId;
    
    private String applicationId;

    private String user;

    private String userMailId;

    private String hostName;

    private String ipAddress;
    
    private Long domainId;
    
    private DomainDto domain;

}
