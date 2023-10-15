package com.employeemanagementsystem.service;

import com.employeemanagementsystem.dto.request.EmployeeSaveRequestDTO;
import com.employeemanagementsystem.dto.request.UpdateEmployeeRequestDTO;
import com.employeemanagementsystem.entity.Employee;

import java.util.List;

public interface EmployeeService {

    com.employeemanagementsystem.entity.Employee save(EmployeeSaveRequestDTO employeeSaveRequestDTO);

    void initRoleAndEmployee();

    List<Employee> getAllEmployee();

    boolean deleteEmployeeById(int id);

    String updateEmployeeByAdmin(int id, UpdateEmployeeRequestDTO updateEmployeeRequestDTO);
}
