package com.example.demo.rolemanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;
    public Role addRole(RoleRequest roleRequest){
        Role role = new Role(roleRequest.getRole());
        return roleRepository.save(role);

    }
}
