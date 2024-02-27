package com.roriga.webbserviceandawsserver.service;


import com.roriga.webbserviceandawsserver.entity.Role;
import com.roriga.webbserviceandawsserver.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role createRole(Role role) {
        // Implementera logik för att skapa en ny roll
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(Long roleId, Role role) {
        // Implementera logik för att uppdatera en befintlig roll
        return roleRepository.findById(roleId)
                .map(existingRole -> {
                    existingRole.setName(role.getName());
                    // Uppdatera ytterligare fält efter behov
                    return roleRepository.save(existingRole);
                }).orElseThrow(() -> new RuntimeException("Rollen hittades inte"));
    }

    @Override
    public void deleteRole(Long roleId) {
        // Implementera logik för att radera en roll
        roleRepository.deleteById(roleId);
    }

    @Override
    public Role findRoleById(Long roleId) {
        // Hämta en roll med specifikt ID
        return roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Rollen hittades inte"));
    }

    @Override
    public List<Role> findAllRoles() {
        // Returnera en lista av alla roller
        return roleRepository.findAll();
    }
}
