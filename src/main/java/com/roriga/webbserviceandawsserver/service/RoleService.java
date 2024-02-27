package com.roriga.webbserviceandawsserver.service;

import com.roriga.webbserviceandawsserver.entity.Role;

import java.util.List;

public interface RoleService {
    Role createRole(Role role);
    Role updateRole(Long roleId, Role role);
    void deleteRole(Long roleId);
    Role findRoleById(Long roleId);
    List<Role> findAllRoles();
}
