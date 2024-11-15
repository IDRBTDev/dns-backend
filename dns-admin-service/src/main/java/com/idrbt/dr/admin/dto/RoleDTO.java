package com.idrbt.dr.admin.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long roleId;
	private String roleName;
	private String roleDescription;
	private String roleStatus;
	private LocalDateTime createdDateTime;
	private LocalDateTime modifiedDateTime;
	private String createdByUser;
	private String modifiedByUser;
	private String createdByEmailId;
	private String modifiedByEmailId;
	
}
