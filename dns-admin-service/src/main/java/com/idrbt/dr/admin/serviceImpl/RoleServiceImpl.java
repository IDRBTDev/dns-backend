package com.idrbt.dr.admin.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.apache.logging.log4j.util.Strings;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idrbt.dr.admin.dto.RoleDTO;
import com.idrbt.dr.admin.entity.Role;
import com.idrbt.dr.admin.repository.RoleRepository;
import com.idrbt.dr.admin.repository.UserRepository;
import com.idrbt.dr.admin.service.RoleService;
import com.idrbt.dr.admin.util.DomainRegistrarConstants;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private ModelMapper mapper;
    
    @Autowired
    private UserRepository userRepository;
  
	@Override
	public RoleDTO saveRole(RoleDTO role) {
		
		log.info("RoleServiceImpl.saveRole() ENTERED");
		if (role == null) {
			//TODO: Thorw new exception 
		}
		if (isRoleNameExists(role)) {
			//TODO: Trhow Role Name Exists Exception
		}
		role.setCreatedDateTime(LocalDateTime.now());
		role.setRoleStatus(DomainRegistrarConstants.STATUS_ACTIVE);	
		Role entity = new Role();
		mapper.map(role, entity);
		Role savedRole = roleRepository.save(entity);
		RoleDTO savedRoleDTO = new RoleDTO();
		mapper.map(savedRole, savedRoleDTO);
		log.info("RoleServiceImpl.saveRole() executed successfully");
		return savedRoleDTO;
	}
	
	@Transactional 
	@Override
	public RoleDTO updateRole(RoleDTO roleDTO) {
		
		log.info("RoleServiceImpl.updateRole() entered with args - role");
		if(roleDTO == null) {
			log.info("RoleServiceImpl.updateRole() EntityNotFoundException : user object is null");
		}
		log.info("RoleServiceImpl.updateRole() is under execution...");
		Optional<Role> optRole = roleRepository.findById(roleDTO.getRoleId());
		Role dbRole = null;
		if(optRole.isEmpty()) {
			log.info("RoleServiceImpl.updateRole() EntityNotFoundException : DB user object is null");
		}
		dbRole = optRole.get();
		//set modified date time
		Role roleToBeUpdated = new Role();
		roleDTO.setModifiedDateTime(LocalDateTime.now());
		mapper.map(roleDTO, roleToBeUpdated);
		Role updatedRole =  roleRepository.save(roleToBeUpdated);
		RoleDTO updatedRoleDTO = new RoleDTO();
		mapper.map(updatedRole, updatedRoleDTO);
		log.info("RoleServiceImpl.updateRole() executed successfully.");
		return updatedRoleDTO;
	}

	@Transactional 
	@Override
	public void deleteRole(Long roleId) {
		log.info("RoleServiceImpl.deleteRole() ENTERED : roleId : " + roleId);
		if (roleId <= 0) {
			log.info("RoleServiceImpl.deleteRole() EmptyInputException : role Id is empty or null");
		}
		//First retrieve the role details
		log.info("RoleServiceImpl.deleteRole() is under execution...");
		Optional<Role> optionRole = roleRepository.findById(roleId);
		
		if ( !optionRole.isPresent() || optionRole == null ) {
			//TODO: throw exception
		} else {
			Long roleCount = roleRepository.findAssignedRoleCount(roleId);
			if(roleCount > 0) {
				//TODO: throw exception
			}
			roleRepository.deleteById(roleId);
		}
		log.info("RoleServiceImpl.deleteRole() executed successfully");
	}

	@Transactional 
	@Override
	public void deleteSelectedRolesByIds(List<Long> roleIds) {
		
		log.info("RoleServiceImpl.deleteSelectedRolesByIds() ENTERED : roleIds Size : " + roleIds.size() );
		if ( roleIds.size() <= 0 ) {
			//TODO: Throw exception
		}
		//Before deletion, find out if the role is attached to any user
		log.info("RoleServiceImpl.deleteSelectedRolesByIds() is under execution..." );
		roleIds.forEach(roleId -> {
			Long roleCount = roleRepository.findAssignedRoleCount(roleId);
			if(roleCount > 0) {
				//TODO: throw exception
			}
		});
		log.info("RoleServiceImpl.deleteSelectedRolesByIds() executed successfully" );
		roleRepository.deleteAllById(roleIds);
	}
	
	@Override
	public List<RoleDTO> getAllRoles() {

		log.info("RoleServiceImpl.getAllRoles() entered.");
		List<Role> roleList = null;
		log.info("getAllRoles() is under execution...");
		roleList = roleRepository.findAllRoles( DomainRegistrarConstants.STATUS_ACTIVE );
		List<RoleDTO> roleDTOList = new ArrayList<>();
		roleList.forEach(role -> {
			RoleDTO dto = new RoleDTO();
			mapper.map(role, dto);
			roleDTOList.add(dto);
		});
		log.info("RoleServiceImpl.getAllRoles() executed successfully");
		return roleDTOList;
	}

	@Override
	public RoleDTO getRoleById(Long roleId) {
		
		log.info("RoleServiceImpl.getRoleById() ENTERED : roleId : " + roleId);
		if (roleId <= 0) {
			log.info("RoleServiceImpl.getRoleById() EmptyInputException : roleId is empty or zero.");
			//TODO: Throw exception
		}
		log.info("RoleServiceImpl.getRoleById() is under execution...");
		Optional<Role> optRole = roleRepository.findById(roleId);
	    Role role = optRole.orElseThrow(() -> new EntityNotFoundException(
	            "Role Entity is Not found in DB !"));
	    RoleDTO roleDTO = new RoleDTO();
	    mapper.map(role, roleDTO);
	    log.info("RoleServiceImpl.getRoleById() executed successfully");
	    return roleDTO;
	}

	@Override
	public RoleDTO getRoleByName(String roleName) {
		
		log.info("RoleServiceImpl.getRoleByName() : roleName : " + roleName);
		log.info("RoleServiceImpl.getRoleByName() is under execution...");
		
		if ( Strings.isNotEmpty(roleName)|| roleName.isEmpty()) {
		    log.info("getRoleByName() EmptyInputException : roleName is empty or null.");
			//TODO: Throw Exception
		}
		Optional<Role> optRole = roleRepository.findByRoleName(roleName);
	    Role role = optRole.orElseThrow(() -> new EntityNotFoundException(
	            "Role not found in DB "));
	    RoleDTO roleDTO = new RoleDTO();
	    mapper.map(role, roleDTO);
		log.info("RoleServiceImpl.getRoleByName() executed successfully");
	    return roleDTO;
	}

	public boolean isRoleNameExists(RoleDTO role) {
		
		log.info("RoleServiceImpl.isRoleNameExists() ENTERED : role : " );
		boolean isRoleNameExists = false;	
		if (role == null) {
			  log.info("RoleServiceImpl.isRoleNameExists() EmptyInputException : role object is null.");
			//TODO: Throw Exception
		} else {
			log.info("RoleServiceImpl.isRoleNameExists()() is under execution..." );
			log.info("RoleServiceImpl.isRoleNameExists()  : Role Id : " + role.getRoleId() + " || Role Name : " + role.getRoleName());
			Optional<Role> optRole = roleRepository.findByRoleName( role.getRoleName() );
			isRoleNameExists = optRole.isPresent();
			log.info("isRoleNameExists : " + isRoleNameExists);
		}
		log.info("RoleServiceImpl.isRoleNameExists() executed successfully" );
		return isRoleNameExists;
	}
	
}
