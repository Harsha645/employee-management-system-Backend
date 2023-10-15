package com.employeemanagementsystem.service.impl;

import com.employeemanagementsystem.entity.Role;
import com.employeemanagementsystem.repisitory.RoleRepo;
import com.employeemanagementsystem.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceIMPL implements RoleService {
    @Autowired
    private RoleRepo roleRepo;
    @Override
    public Role createNewRole(Role role) {

        return roleRepo.save(role);
    }
}
