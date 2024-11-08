package com.example.demo.rolemanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;
    @PostMapping
    public Role addRole(@RequestBody RoleRequest roleRequest){
        return roleService.addRole(roleRequest);
    }
}
