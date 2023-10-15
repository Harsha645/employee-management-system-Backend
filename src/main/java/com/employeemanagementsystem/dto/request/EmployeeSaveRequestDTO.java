package com.employeemanagementsystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeSaveRequestDTO {
    private String name;
    private String email;
    private String contact_number;
    private String password;
}
