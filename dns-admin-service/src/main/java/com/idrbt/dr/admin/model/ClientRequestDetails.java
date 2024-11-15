package com.idrbt.dr.admin.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientRequestDetails {
	
	private String query;
	private String country;
    private String countryCode;
	private String regionName;
	private String city;
	private String timezone;

}
