package com.idrbt.dr.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idrbt.dr.admin.dto.RoleDTO;
import com.idrbt.dr.admin.service.RoleService;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/dr/roles")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@PostMapping("/save")
	public ResponseEntity<RoleDTO> saveRole(@RequestBody RoleDTO role) {
		log.info("saveRole() entered with args - role");
		if (role == null) {
			log.info("saveRole() EntityNotFoundException : Role object is null or empty.");
			//TODO : Throw Exception
		}
		try {
			log.info("saveRole() is under execution.");
			log.info(":Role Object : " + role );
			//assign the corresponding permission object to role
			var createdRole = roleService.saveRole(role);
			log.info("saveRole() executed successfully.");
			return new ResponseEntity<>(createdRole, HttpStatus.CREATED);
		} catch (EntityNotFoundException roleBusinessException) {
			log.error("Role Business Exception has encountered while saving Role. " + roleBusinessException.getMessage(), roleBusinessException);
			throw roleBusinessException;
		} catch (Exception e) {
			log.error("General Exception has encountered while saving Role. " + e.getMessage(), e);
			//TODO: throw exception
			throw e;
		}
	}

	@PutMapping("/update")
	public ResponseEntity<RoleDTO> updateRole(@RequestBody RoleDTO role) {
		log.info("updateRole() entered with args - role");
		if (role == null) {
			log.info("updateRole() EntityNotFoundException Role object is null or empty.");
			//TODO : Throw Exception
		}
		try {
			log.info("updateRole() is under execution...");
			var updatedRole = roleService.updateRole(role);
			log.info("updateRole() executed successfully.");
			return new ResponseEntity<>(updatedRole, HttpStatus.CREATED);
		}catch (EntityNotFoundException roleBusinessException) {
			log.error("Role Business Exception has encountered while updating Role. " + roleBusinessException.getMessage(), roleBusinessException);
			throw roleBusinessException;
		}catch (Exception e) {
			log.error("General Exception has encountered while updating Role. " + e.getMessage(), e);
			//TODO : Throw Exception
			throw e;
		}
	}

	@DeleteMapping("/{ids}")
	public ResponseEntity<Boolean> deleteSelectedRoles(@PathVariable("ids") List<Long> roleIds) {
		log.info("deleteSelectedRoles() entered with args - ids");
		if (roleIds == null || roleIds.isEmpty() ) {
			log.info("deleteSelectedRoles() EmptyInputException : role Id is empty");
			//TODO : Throw Exception
		}
		try {
			log.info("deleteSelectedRoles() is under execution... : roleIds size (): " + roleIds.size());
			roleService.deleteSelectedRolesByIds(roleIds);
			log.info("deleteSelectedRoles() executed successfully");
			return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
		}catch (Exception businessException) {
			log.error("deleteSelectedRoles() exited with Business exception : Exception occured fetching roles list."
					+ businessException.getMessage(), businessException);
			throw businessException;
		} 
		
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<RoleDTO>> getAllRoles() {
		log.info("getAllRoles() ENTERED.");
		try {
			log.info("getAllRoles() is under execution...");
			var rolesList = roleService.getAllRoles();
			log.info("getAllRoles() executed successfully");
			return new ResponseEntity<>(rolesList, HttpStatus.OK);
		}catch (Exception businessException) {
			log.error("getAllRoles() exited with exception : Exception occured fetching roles list."
					+ businessException.getMessage(), businessException);
			throw businessException;
		} 

	}

	@GetMapping("/{roleId}")
	public ResponseEntity<RoleDTO> getRoleById(@PathVariable Long roleId) {
		log.info("getRoleById() ENTERED : roleId : " + roleId);
		if (roleId == null || roleId <= 0) {
			log.info("getRoleById() EmptyInputException : role Id is empty");
			//TODO : Throw Exception
		}
		try {
			log.info("getRoleById() is under execution...");
			var role = roleService.getRoleById(roleId);
			log.info("getRoleById() executed successfully");
			return new ResponseEntity<>(role, HttpStatus.OK);
		}catch (Exception businessException) {
			log.error("getRoleById() exited with exception : Exception occured fetching role."
					+ businessException.getMessage(), businessException);
			throw businessException;
		}
		
	}



}
