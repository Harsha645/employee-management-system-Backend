package com.employeemanagementsystem.service.impl;

import com.employeemanagementsystem.dto.request.EmployeeSaveRequestDTO;
import com.employeemanagementsystem.dto.request.UpdateEmployeeRequestDTO;
import com.employeemanagementsystem.entity.Employee;
import com.employeemanagementsystem.entity.Role;
import com.employeemanagementsystem.repisitory.EmployeeRepo;
import com.employeemanagementsystem.repisitory.RoleRepo;
import com.employeemanagementsystem.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class EmployeeServiceIMPL implements EmployeeService {
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Employee save(EmployeeSaveRequestDTO employeeSaveRequestDTO) {
        Role roles = roleRepo.findById("USER").get();

        Set<Role> empRoles = new HashSet<>();
        empRoles.add(roles);

        Employee employee = modelMapper.map(employeeSaveRequestDTO, Employee.class);
        employee.setRole(empRoles);
        employeeRepo.save(employee);
        return employee;
    }

    public void initRoleAndEmployee() {
        Role adminRole = new Role();
        Role employeeRole = new Role();

        if (!roleRepo.existsById("ADMIN")) {
            adminRole.setRoleName("ADMIN");
            adminRole.setRoleDescription("admin role");
            roleRepo.save(adminRole);
        }

        if (!roleRepo.existsById("USER")) {
            employeeRole.setRoleName("USER");
            employeeRole.setRoleDescription("user role");
            roleRepo.save(employeeRole);
        }
        if (!employeeRepo.existsById(1)) {
            com.employeemanagementsystem.entity.Employee employee = new com.employeemanagementsystem.entity.Employee();
            employee.setName("admin123");
            employee.setEmail("admin@gmail.com");
            employee.setContact_number("0719228889");
            employee.setPassword("admin@123");

            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(adminRole);

            employee.setRole(adminRoles);
            employeeRepo.save(employee);
        }
        if (!employeeRepo.existsById(2)) {
            com.employeemanagementsystem.entity.Employee employee = new com.employeemanagementsystem.entity.Employee();
            employee.setName("employee123");
            employee.setEmail("employee@gmail.com");
            employee.setContact_number("0702228850");
            employee.setPassword("employee@123");

            Set<Role> employeeRoles = new HashSet<>();
            employeeRoles.add(employeeRole);

            employee.setRole(employeeRoles);
            employeeRepo.save(employee);
        }
    }

    @Override
    public List<Employee> getAllEmployee() {
        List<Employee> employees = employeeRepo.findAll();
//        List<Employee> employeeResponseDTOList = modelMapper.map(employees,  new TypeToken(){
//                }.getType()
//        );
        return employees;
    }

    @Override
    public boolean deleteEmployeeById(int id) {
       employeeRepo.deleteById(id);
       return true;
    }

    @Override
    public String updateEmployeeByAdmin(int id, UpdateEmployeeRequestDTO updateEmployeeRequestDTO) {
        Employee employee = employeeRepo.findById(id).get();
        employee.setName(updateEmployeeRequestDTO.getName());
        employee.setEmail(updateEmployeeRequestDTO.getEmail());
        employee.setContact_number(updateEmployeeRequestDTO.getContact_number());

        employeeRepo.save(employee);
        return "Updated";
    }
}
