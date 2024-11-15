package com.idrbt.dr.nameserver.model;

import com.idrbt.dr.nameserver.dto.DomainDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "name_server")
public class NameServer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nameServerId;

    private String applicationId;

    @Column(name = "user_name")
    private String user;

    private String userMailId;

    private String hostName;

    private String ipAddress;
    
    @Column(name = "domainId")
    private Long domainId;

}
