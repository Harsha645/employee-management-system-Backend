package com.employeemanagementsystem.dto.response;

import com.employeemanagementsystem.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginResponse {
    private Employee employee;
    private String jwtToken;
}
