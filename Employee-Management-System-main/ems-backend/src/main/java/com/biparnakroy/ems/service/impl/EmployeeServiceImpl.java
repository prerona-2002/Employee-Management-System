package com.biparnakroy.ems.service.impl;

import com.biparnakroy.ems.entity.Employee;
import com.biparnakroy.ems.exception.ResourceNotFoundException;
import com.biparnakroy.ems.mapper.EmployeeMapper;
import com.biparnakroy.ems.models.EmployeeDto;
import com.biparnakroy.ems.repository.EmployeeRepository;
import com.biparnakroy.ems.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    private static final Logger log = LogManager.getLogger(EmployeeService.class);

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        try{
            log.info("Creating employee with data: {}", employeeDto);
            Employee employee = employeeMapper.mapToEmployee(employeeDto);
            Employee savedEmployee=employeeRepository.save(employee);
            log.info("Employee created successfully with id: {}", savedEmployee.getId());
            return employeeMapper.mapToEmployeeDto(savedEmployee);
        }catch (Exception e) {
            log.error("An error occurred while creating the employee with data: {}", employeeDto, e);
            throw new RuntimeException("An error occurred while creating the employee", e);
        }
    }

    @Override
    public EmployeeDto getEmployee(Long employeeId) {
        try {
            log.info("Finding Employee with given id: {}", employeeId);
            Employee employee = employeeRepository.findById(employeeId)
                    .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with given id: " + employeeId));
            log.info("Found Employee {} with given id {}",employee,employeeId);
            return employeeMapper.mapToEmployeeDto(employee);
        } catch (ResourceNotFoundException e) {
            log.error("An error occurred while fetching the employee",e);
            throw new ResourceNotFoundException("Employee does not exist with given id: " + employeeId);
        } catch (Exception e) {
            log.error("An error occurred while fetching the employee",e);
            throw new RuntimeException("An error occurred while fetching the employee", e);
        }
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        try {
            log.info("Fetching all employees");
            List<Employee> allEmployees = employeeRepository.findAll();
            List<EmployeeDto> employeeDtos = allEmployees.stream()
                    .map(employeeMapper::mapToEmployeeDto).sorted((dto1, dto2) -> Long.compare(dto1.getId(), dto2.getId())).collect(Collectors.toList());
            log.info("Successfully fetched {} employees", employeeDtos.size());
            return employeeDtos;
        } catch (Exception e) {
            log.error("An error occurred while fetching all employees", e);
            throw new RuntimeException("An error occurred while fetching all employees", e);
        }
    }

    @Override
    public EmployeeDto editEmployeeData(Long employeeId, EmployeeDto employeeDto) {
        try {
            log.info("Editing Employee with given id: {}", employeeId);
            Employee employeeToBeUpdated = employeeRepository.findById(employeeId)
                    .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with given id: " + employeeId));
            employeeToBeUpdated.setFirstName(employeeDto.getFirstName());
            employeeToBeUpdated.setLastName(employeeDto.getLastName());
            employeeToBeUpdated.setEmail(employeeDto.getEmail());
            Employee savedEmployee = employeeRepository.save(employeeToBeUpdated);
            log.info("Changed Employee with id: {} employee : {}", employeeId,savedEmployee);
            return employeeMapper.mapToEmployeeDto(savedEmployee);
        }catch (ResourceNotFoundException e) {
            log.error("An error occurred while fetching the employee",e);
            throw new ResourceNotFoundException("Employee does not exist with given id: " + employeeId);
        } catch (Exception e) {
            log.error("An error occurred while Editing the employee",e);
            throw new RuntimeException("An error occurred while Editing the employee", e);
        }
    }

    public EmployeeDto deleteEmployee(long employeeId) {
        try {
            log.info("Deleting Employee with given id: {}", employeeId);
            Employee employeeToBeDeleted = employeeRepository.findById(employeeId)
                    .orElseThrow(() -> new ResourceNotFoundException("Employee does not exist with given id: " + employeeId));
            employeeRepository.deleteById(employeeId);
            return employeeMapper.mapToEmployeeDto(employeeToBeDeleted);
        }catch (ResourceNotFoundException e) {
            log.error("An error occurred while fetching the employee",e);
            throw new ResourceNotFoundException("Employee does not exist with given id: " + employeeId);
        } catch (Exception e) {
            log.error("An error occurred while Deleting the employee",e);
            throw new RuntimeException("An error occurred while Deleting the employee", e);
        }
    }
}
