package com.idrbt.dr.admin.service;

import java.util.List;

import com.idrbt.dr.admin.dto.RoleDTO;

public interface RoleService {
	
	public List<RoleDTO> getAllRoles();
    public RoleDTO getRoleById(Long id);
    public RoleDTO getRoleByName(String name) ;
    public RoleDTO saveRole(RoleDTO roleDTO) ;
    public RoleDTO updateRole(RoleDTO roleDTO);	       
    public void deleteRole(Long id) ;
    public void deleteSelectedRolesByIds(List<Long> ids);

}
