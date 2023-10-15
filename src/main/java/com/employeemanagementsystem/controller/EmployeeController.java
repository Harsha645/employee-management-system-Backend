package com.employeemanagementsystem.controller;

import com.employeemanagementsystem.dto.request.EmployeeSaveRequestDTO;
import com.employeemanagementsystem.dto.request.UpdateEmployeeRequestDTO;
import com.employeemanagementsystem.entity.Employee;
import com.employeemanagementsystem.service.EmployeeService;
import com.employeemanagementsystem.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("api/v1/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostConstruct
    public void initRoleAndEmployee() {
        employeeService.initRoleAndEmployee();
    }

    @PostMapping("/save")
    public ResponseEntity<StandardResponse> registerNewEmployee(@RequestBody EmployeeSaveRequestDTO employeeSaveRequestDTO) {
        com.employeemanagementsystem.entity.Employee savedEmployee = employeeService.save(employeeSaveRequestDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "saved", savedEmployee), HttpStatus.CREATED
        );
    }

    @GetMapping("/get-all")
    public ResponseEntity<StandardResponse> getAllEmployee() {
        List<Employee> employeeResponseDTOList = employeeService.getAllEmployee();
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Get all Employees successfully", employeeResponseDTOList), HttpStatus.OK
        );
    }

    @DeleteMapping(
            path = {"/delete-by-id/{id}"}
    )
    public ResponseEntity<StandardResponse> deleteEmployeeById(@PathVariable(value = "id") int id) {
        boolean deletedEmployee = employeeService.deleteEmployeeById(id);

        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Successfully Deleted", deletedEmployee), HttpStatus.OK
        );
    }

    @PutMapping(
            path = {"/update-employee-by-admin/{id}"})
    public ResponseEntity<StandardResponse> updateEmployeeByAdmin(@PathVariable(value = "id") int id, @RequestBody UpdateEmployeeRequestDTO updateEmployeeRequestDTO) {
        System.out.println(id);
        System.out.println(updateEmployeeRequestDTO);
        String updateMsg = employeeService.updateEmployeeByAdmin(id, updateEmployeeRequestDTO);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Successfully updated", updateMsg), HttpStatus.OK
        );
    }

}
