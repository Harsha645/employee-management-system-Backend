package com.employeemanagementsystem.controller;

import com.employeemanagementsystem.entity.Role;
import com.employeemanagementsystem.service.RoleService;
import com.employeemanagementsystem.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/role")
@CrossOrigin
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping("/create-new-role")
    public ResponseEntity<StandardResponse> createNewRole(@RequestBody Role role) {
        Role newRole = roleService.createNewRole(role);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, newRole + " Successfully Created", newRole),
                HttpStatus.CREATED
        );
    }
}
