package com.idrbt.dr.admin.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table (name = "role_tab")
public class Role {

	@Id
	@Column(name = "role_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long roleId;
	
	@Column(name = "roleName", nullable = false)
	private String roleName;
	
	@Column(name = "roleDescription", unique = false)
	private String roleDescription;
	
	@Column(name = "roleStatus", nullable = true)
	private String roleStatus;
	
	@Column(name = "createdDateTime")
	private LocalDateTime createdDateTime;
	
	@Column(name = "modifiedDateTime", nullable = true)
	private LocalDateTime modifiedDateTime;
	
	@Column(name = "createdBy")
	private String createdBy;
	
	@Column(name = "modifiedBy")
	private String modifiedBy;
	
	@Column(name = "createdByEmailId")
	private String createdByEmailId;
	
	@Column(name = "modifiedByEmailId", nullable = true)
	private String modifiedByEmailId;
	
}
