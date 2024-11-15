package com.idrbt.dr.admin.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.idrbt.dr.admin.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	 @Query("FROM Role WHERE roleName=:name AND roleStatus='Active'")
	 Optional<Role> findByRoleName(String name);
	 
	 @Query ("FROM Role WHERE roleStatus=:roleStatus")
	 List<Role> findAllRoles(String roleStatus);
	 
	 @Query(value = "SELECT COUNT(*) FROM user_role_tab WHERE role_id=:roleId",nativeQuery = true )
	 Long findAssignedRoleCount(Long roleId);
	 
}
