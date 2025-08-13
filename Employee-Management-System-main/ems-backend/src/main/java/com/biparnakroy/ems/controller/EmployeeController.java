package com.biparnakroy.ems.controller;

import com.biparnakroy.ems.models.EmployeeDto;
import com.biparnakroy.ems.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin("http://localhost:3000")
@AllArgsConstructor
@RestController
@RequestMapping("/api/employees/")
public class EmployeeController {

    private EmployeeService employeeService;

    // add Employee REST API
    @PostMapping(path = "createEmployee")
    public ResponseEntity<EmployeeDto> createEmployee(@Valid @RequestBody final EmployeeDto employeeDto){
        final EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    // get employee by id REST API
    @GetMapping("getEmployee/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId){
        EmployeeDto foundEmployee=employeeService.getEmployee(employeeId);
        return new ResponseEntity<>(foundEmployee,HttpStatus.OK);
    }

    @GetMapping(path = "getAllEmployees")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        return new ResponseEntity<>((employeeService.getAllEmployees()), HttpStatus.OK);
    }

    @PutMapping(path = "editEmployee/{id}")
    public ResponseEntity<EmployeeDto> editEmployee(@PathVariable("id") Long employeeId, @Valid @RequestBody EmployeeDto employeeDtoToUpdate) {
        return new ResponseEntity<>(employeeService.editEmployeeData(employeeId,employeeDtoToUpdate), HttpStatus.OK);

    }

    @DeleteMapping(path = "deleteEmployee/{id}")
    public ResponseEntity<EmployeeDto> deleteEmployee(@PathVariable("id") Long employeeId) {
        return new ResponseEntity<>(employeeService.deleteEmployee(employeeId), HttpStatus.OK);
    }
}
