package com.idrbt.dr.admin.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "user_tab")
@Data
public class User {

	@Id
	@Column(name = "user_id", nullable = false, unique = true)
	private String userId;

	@Column(name = "mobile_number", nullable = false, unique = true)
	private String mobileNumber;
	
	@Column(name = "encrypted_password", nullable = false, unique = false)
	private String encryptedPassword;
	
	@Column(name="previous_Password",nullable=true, unique = false)
	private String previousPassword;
	
	@Column(name="previous_password_one",nullable=true, unique = false)
	private String previousPasswordOne;
	
	@Column(name="previous_password_two",nullable=true, unique = false)
	private String previousPasswordTwo;
	
//	@ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.PERSIST}, fetch = FetchType.EAGER)
//	@JoinTable(
//				name = "user_role_tab",
//				joinColumns = @JoinColumn(name = "user_id"),
//				inverseJoinColumns = @JoinColumn(name = "role_id")
//			)
	//private Set<Role> userRoles = new HashSet<>();
	
	@Column(name = "two_factor_auth_enabled", nullable = true, unique = false)
	private boolean twoFactorAuthEnabled;	
	
	@Column(name ="profile_picture",nullable=true)
	private byte[] profilePicture;
	
	@Column(name = "is_active")
	private boolean isActive;
	
	@Column(name = "login_attempt_count")
	private int loginAttemptCount;
	
	@Column(name = "last_login_ip_address")
	private String lastLoginIpAddress;
	
	@Column(name = "last_login_device_type")
	private String lastLoginDeviceType;
	
	@Column(name = "last_login_datetime")
	private LocalDateTime lastLoginDatetime;
	
	@Column(name = "created_datetime")
	private LocalDateTime createdDatetime;
	
	@Column(name = "updated_datetime")
	private LocalDateTime updatedDatetime;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "updated_by")
	private String updatedBy;
	
	@Column(name = "created_by_email")
	private String createdByEmail;
	
	@Column(name = "updated_by_email")
	private String updatedByEmail;
	
	@Column(name = "last_successful_login_datetime", nullable = true)
	private LocalDateTime lastSuccessfulLoginDatetime;
	
}
