package com.biparnakroy.ems.service;

import com.biparnakroy.ems.models.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployee(Long employeeId);

    List<EmployeeDto> getAllEmployees();

    EmployeeDto editEmployeeData(Long Id,EmployeeDto employeeDto);

    EmployeeDto deleteEmployee(long employeeId);
}
