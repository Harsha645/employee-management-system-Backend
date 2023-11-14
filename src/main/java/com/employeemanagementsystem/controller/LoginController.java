package com.employeemanagementsystem.controller;

import com.employeemanagementsystem.dto.request.LoginRequest;
import com.employeemanagementsystem.dto.response.LoginResponse;
import com.employeemanagementsystem.service.JwtService;
import com.employeemanagementsystem.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping
public class LoginController {
    @Autowired
    private JwtService jwtService;

    @PostMapping("/authentication")
    public ResponseEntity<StandardResponse> createJwtTokenAndLogin(@RequestBody LoginRequest loginRequest) throws Exception {
        System.out.println(loginRequest);
        LoginResponse login = jwtService.createJwtToken(loginRequest);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Login and create Jwt Token",login), HttpStatus.OK
        );
    }
}
