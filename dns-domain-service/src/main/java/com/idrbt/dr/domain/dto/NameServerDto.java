package com.idrbt.dr.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NameServerDto {
	
    private int serialNumber;
    
    private String applicationId;

    private String user;

    private String userMailId;

    private String hostName;

    private String ipAddress;
    
    private Long domainId;

}
